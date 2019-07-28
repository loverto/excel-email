package org.ylf.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.commons.collections4.ListUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.ylf.domain.Gongjijin;
import org.ylf.domain.GongjijinHistory;
import org.ylf.domain.SheBao;
import org.ylf.domain.SheBaoHistory;
import org.ylf.domain.UserInfo;
import org.ylf.repository.GongjijinHistoryRepository;
import org.ylf.repository.GongjijinRepository;
import org.ylf.repository.SheBaoHistoryRepository;
import org.ylf.repository.SheBaoRepository;
import org.ylf.repository.UserInfoRepository;

@Service
public class MailUtilService{

    private final Logger log = LoggerFactory.getLogger(MailUtilService.class);

    private final GongjijinRepository gongjijinRepository;

    private final SheBaoRepository sheBaoRepository;

    private final GongjijinHistoryRepository gongjijinHistoryRepository;

    private final SheBaoHistoryRepository sheBaoHistoryRepository;

    private final UserInfoRepository userInfoRepository;
    private final JavaMailSender mailSender;

    public MailUtilService(GongjijinRepository gongjijinRepository, SheBaoRepository sheBaoRepository,
                           UserInfoRepository userInfoRepository,JavaMailSender mailSender,GongjijinHistoryRepository gongjijinHistoryRepository,SheBaoHistoryRepository sheBaoHistoryRepository){
        this.gongjijinRepository = gongjijinRepository;
        this.sheBaoRepository = sheBaoRepository;
        this.userInfoRepository = userInfoRepository;
        this.mailSender = mailSender;
        this.gongjijinHistoryRepository = gongjijinHistoryRepository;
        this.sheBaoHistoryRepository = sheBaoHistoryRepository;
    }


    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, String.valueOf(value));
        }
        return map;
    }

    @Async
    public void sendMail(String outDataPath,String templateUrl,String from,String mailSubject,String mailContent) throws InterruptedException {
        List<UserInfo> mailBeans = userInfoRepository.findAll();

        splitExcelAndMergeExcel(outDataPath,templateUrl);

        //String deliver = "bksx@bksx.cn";
        String deliver = from;
        String [] s = {"1045438139@qq.com"};
        String subject = mailSubject;
        String content = mailContent;
        boolean isHtml = true;
        String fileName = "A";

        long sleep = 5;

        File dir = new File(outDataPath);

        File[] files = dir.listFiles((f,ff)->{return ff.endsWith(".xlsx");});

        List<File> files1 = Arrays.asList(files);
        for (int i =0;i<files1.size();i++){
            File file = files1.get(i);

            String name = file.getName();

            log.info("file name:{}",name);

            String[] split = name.split("\\.");
            String username = split[0];

            Optional<UserInfo> first = mailBeans.stream().filter(a -> username.equals(a.getName())).findFirst();

            if(first.isPresent()){
                UserInfo mailBean = first.get();
                log.info("给 {} 发送邮件", mailBean.getName());
                s[0] = mailBean.getInternetMail();
                content = content.replace("{ username }",username);
                //发送邮件
                sendAttachmentFileEmail(mailSender,deliver, s, s, subject, content, isHtml, name, file);
                Thread.sleep(sleep*1000);
            };



        }
    }




    /**
     * 发送带附件的邮件
     * @param deliver
     * @param receiver
     * @param carbonCopy
     * @param subject
     * @param content
     * @param isHtml
     * @param fileName
     * @param file
     */
    @Async
    public void sendAttachmentFileEmail(JavaMailSender mailSender, String deliver, String[] receiver, String[] carbonCopy, String subject, String content, boolean isHtml, String fileName, File file) {
        long startTimestamp = System.currentTimeMillis();
        log.info("Start send mail ...");

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            messageHelper.setFrom(deliver);
            messageHelper.setTo(receiver);
            messageHelper.setCc(carbonCopy);
            messageHelper.setSubject(subject);
            content += "\n\n\n\n\n-------------本次邮件发送服务由新技术研发部提供技术支持--------------";
            messageHelper.setText(content, isHtml);
            messageHelper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("Send mail success, cost {} million seconds", System.currentTimeMillis() - startTimestamp);
        } catch (MessagingException e) {
            log.error("Send mail failed, error message is {}\n", e.getMessage());
            e.printStackTrace();
            //throw new ServiceException(e.getMessage());
        }
    }


    /**
     * 拆分合并Excel ,存储到D盘的Excel目录下
     * @param outDataPath 输出数据的目录
     * @param templateUrl 模板文件目录
     */
    public void splitExcelAndMergeExcel(String outDataPath,String templateUrl){
        try {

            List<SheBao> SheBaos = sheBaoRepository.findAll();
            ArrayList<SheBao> unique  = SheBaos.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                    () -> new TreeSet<>(Comparator.comparing(o -> o.getName()))), ArrayList::new));

            List<SheBao> subtract = ListUtils.subtract(SheBaos, unique);
            for (SheBao s: subtract){
                String name = s.getName();
                log.info("重复的人员,没有发送邮件{}",s);
            }
            log.info("共计 {} 人, 重复 {} 人",SheBaos.size(),SheBaos.size()-unique.size());
            List<Gongjijin> Gongjijins = gongjijinRepository.findAll();
            TemplateExportParams templateExportParams = new TemplateExportParams(
                templateUrl);

            Map<String, Object> map = new HashMap<String, Object>();
            int sbSize = SheBaos.size();
            int size = Gongjijins.size();
            int s = (sbSize>size)?size:sbSize;
            for (int i = 0; i< s; i++){
                SheBao value = SheBaos.get(i);
                SheBaoHistory sheBaoHistory = new SheBaoHistory();
                BeanUtils.copyProperties(value, sheBaoHistory);
                sheBaoRepository.delete(value);
                sheBaoHistoryRepository.save(sheBaoHistory);
                List<Map<String,String>> a = new ArrayList();

                Map<String, String> stringObjectMap = objectToMap(value);
                a.add(stringObjectMap);
                map.put("sb", a);
                Optional<Gongjijin> value1 = Gongjijins.parallelStream().filter((Gongjijin g) -> value.getName().equals(g.getName())).findFirst();
                List<Map<String, String>> b = new ArrayList();

                Gongjijin gongjijin = value1.get();
                GongjijinHistory gongjijinHistory = new GongjijinHistory();
                BeanUtils.copyProperties(gongjijin, gongjijinHistory);
                gongjijinRepository.delete(gongjijin);
                gongjijinHistoryRepository.save(gongjijinHistory);
                Map<String, String> stringObjectMap1 = objectToMap(gongjijin);
                b.add(stringObjectMap1);
                map.put("gjj", b);

                String name = value.getName();
                templateExportParams.setSheetName(name);

                Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
                File savefile = new File(outDataPath);
                if (!savefile.exists()) {
                    savefile.mkdirs();
                }
                String name1 = outDataPath + name + ".xlsx";
                File file = new File(name1);
                if (file.exists()){
                    file.renameTo(new File(file.getAbsolutePath()+file.getName()+file.lastModified()));
                }
                FileOutputStream fos = new FileOutputStream(name1);
                workbook.write(fos);
                fos.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
