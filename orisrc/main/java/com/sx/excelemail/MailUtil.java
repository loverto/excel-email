package com.sx.excelemail;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.sx.excelemail.po.GongjijinBean;
import com.sx.excelemail.po.MailBean;
import com.sx.excelemail.po.ShebaoBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MailUtil {

    /**
     * 读取用户
     * @return
     */
    public List<MailBean> readUser(String userDataPath){
        try {
            ImportParams params = new ImportParams();
            //params.setNeedSave(true);
            List<MailBean> result = ExcelImportUtil.importExcel(
                    (new File(userDataPath)),
                    MailBean.class, params);
            for (int i = 0; i < result.size(); i++) {
                System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
            }
            return result;
            //Assert.assertTrue(result.size() == 1);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("出问题了,找管理员");
            return null;
        }
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


    /**
     * 发邮件
     * @param deliver
     * @param receiver
     * @param carbonCopy
     * @param subject
     * @param content
     * @param isHtml
     * @param fileName
     * @param file
     */
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
     * @param dataPath 数据目录
     * @param outDataPath 输出数据的目录
     * @param templateUrl 模板文件目录
     */
    public void splitExcelAndMergeExcel(String dataPath,String outDataPath,String templateUrl){
        try {

            ImportParams params = new ImportParams();
            params.setHeadRows(3);
            //params.setTitleRows(3);
            //params.setNeedSave(true);
            List<ShebaoBean> shebaoBeans = ExcelImportUtil.importExcel(
                    new File(dataPath),
                    ShebaoBean.class, params);

            ArrayList<ShebaoBean> unique  = shebaoBeans.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(o -> o.getName()))), ArrayList::new));

            List<ShebaoBean> subtract = ListUtils.subtract(shebaoBeans, unique);

            for (ShebaoBean s: subtract){
                String name = s.getName();
                log.info("重复的人员,没有发送邮件{}",s);
            }


            log.info("共计 {} 人, 重复 {} 人",shebaoBeans.size(),shebaoBeans.size()-unique.size());

            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(3);
            importParams.setStartSheetIndex(1);
            importParams.setSheetNum(1);
            //importParams.setNeedSave(true);
            List<GongjijinBean> gongjijinBeans = ExcelImportUtil.importExcel(
                    new File(dataPath),
                    GongjijinBean.class, importParams);

            TemplateExportParams templateExportParams = new TemplateExportParams(
                    templateUrl);



            Map<String, Object> map = new HashMap<String, Object>();
            int sbSize = shebaoBeans.size();
            int size = gongjijinBeans.size();
            int s = (sbSize>size)?size:sbSize;
            for (int i = 0; i< s; i++){
                ShebaoBean value = shebaoBeans.get(i);
                List<Map<String,String>> a = new ArrayList();


                Map<String, String> stringObjectMap = objectToMap(value);
                a.add(stringObjectMap);
                map.put("sb", a);
                GongjijinBean value1 = gongjijinBeans.get(i);
                List<Map<String, String>> b = new ArrayList();
                Map<String, String> stringObjectMap1 = objectToMap(value1);
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
