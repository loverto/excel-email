package com.sx.excelemail.resource;

import com.sx.excelemail.MailUtil;
import com.sx.excelemail.po.MailBean;
import com.sx.excelemail.po.WuxianyijinConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class WuxianyijinResource {

    @Autowired
    MailUtil mailUtil;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    Environment environment;

    /**
     * 要处理的数据文件
     */
    private  String dataPath = "d:/upload";
    private  String dataFilename = "test.xlsx";
    private  String templateUrl = "d:/upload";
    private  String templateFilename = "template.xlsx";
    private  String userDataPath = "d:/upload";
    private  String userDataFilename = "user.xlsx";
    private  String outDataPath = "D:/excel/";
    private String from = "yinlongfei@aliyun.com";
    private String mailSubject = "测试公积金";
    private String mailContent = "测试公积金";


    @PostConstruct
    public void init(){
        dataPath = environment.getProperty("data.path",dataPath);
        dataFilename = environment.getProperty("data.filename",dataFilename);
        templateUrl = environment.getProperty("template.path",templateUrl);
        templateFilename = environment.getProperty("template.filename",templateFilename);
        userDataPath = environment.getProperty("user.data.path",userDataPath);
        userDataFilename = environment.getProperty("user.data.filename",userDataFilename);
        outDataPath = environment.getProperty("out.data.path",outDataPath);

        from = environment.getProperty("mail.from",from);
        mailSubject = environment.getProperty("mail.subject",mailSubject);
        mailContent = environment.getProperty("mail.content",mailContent);

    }



    @PostMapping("/wxyj")
    public String wuxianyijinConfiguration(WuxianyijinConfiguration wuxianyijinConfiguration) throws Exception{

        try {



            MultipartFile dataExcel = wuxianyijinConfiguration.getDataExcel();
            MultipartFile userExcel = wuxianyijinConfiguration.getUserExcel();

            //处理data重名数据
            handlerDumpFileName(dataPath,dataFilename);

            handlerDumpFileName(userDataPath,userDataFilename);

            if(StringUtils.isNotBlank(wuxianyijinConfiguration.getMailContent())){
                mailContent = wuxianyijinConfiguration.getMailContent();
            }

            if (StringUtils.isNotBlank(wuxianyijinConfiguration.getMailSubject())){
                mailSubject = wuxianyijinConfiguration.getMailSubject();
            }



            dataExcel.transferTo(new File(dataPath+File.separator+dataFilename));
            userExcel.transferTo(new File(userDataPath+File.separator+userDataFilename));


            File file = new File(templateUrl + File.separator + templateFilename);
            //如果没有上传模板，默认位置
            if (!file.exists()){
                ClassPathResource classPathResource = new ClassPathResource(templateFilename);
                templateUrl = classPathResource.getFile().getParent();
               templateFilename = classPathResource.getFilename();
            }

            sendMail(userDataPath+File.separator+userDataFilename,dataPath+File.separator+dataFilename,outDataPath,templateUrl+File.separator+templateFilename,from,mailSubject,mailContent);
            return "redirect:/zhengzaifasong.html";
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error.html";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "redirect:/error.html";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }


        //return "redirect:/zhengzaifasong.html";
    }

    private void handlerDumpFileName(String dataPath,String dataFilename) {
        File dataDir = new File(dataPath);
        if(dataDir.exists()){
            File[] files = dataDir.listFiles();
            List<File> files1 = Arrays.asList(files);
            if(files1.stream().filter(file -> file.getName().equals(dataFilename)).count()>0){
                String pathname = dataPath + File.separator + dataFilename;
                File f = new File(pathname);
                f.renameTo(new File(dataPath+File.separator+f.getName()+f.lastModified()));
            }
        }else {
            dataDir.mkdirs();
        }
    }

    private void sendMail(String userDataPath,String dataPath,String outDataPath,String templateUrl,String from,String mailSubject,String mailContent) throws InterruptedException {
        List<MailBean> mailBeans = mailUtil.readUser(userDataPath);

        mailUtil.splitExcelAndMergeExcel(dataPath,outDataPath,templateUrl);

        //String deliver = "bksx@bksx.cn";
        String deliver = from;
        String [] s = {"1045438139@qq.com"};
        String subject = mailSubject;
        String content = mailContent;
        boolean isHtml = false;
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

            Optional<MailBean> first = mailBeans.stream().filter(a -> username.equals(a.getName())).findFirst();

            if(first.isPresent()){
                MailBean mailBean = first.get();
                log.info("给 {} 发送邮件", mailBean.getName());
                s[0] = mailBean.getInternetMail();
                //发送邮件
                mailUtil.sendAttachmentFileEmail(mailSender,deliver, s, s, subject, content, isHtml, name, file);
                Thread.sleep(sleep*1000);
            };



        }
    }
}
