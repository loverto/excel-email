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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExcelEmailApplicationTests {
    /**
     * 要处理的数据文件
     */
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


    @Autowired
    MailUtil mailUtil;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    Environment environment;
    @Before
    public void setUp(){
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



	@Test
	public void contextLoads() throws Exception {
       // sendMail(userDataPath+File.separator+userDataFilename,dataPath+File.separator+dataFilename,outDataPath,templateUrl+File.separator+templateFilename,from,mailSubject,mailContent);
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;

        String username = "1174927238@qq.com";
        javaMailSender.setUsername(username);
        javaMailSender.setPort(465);
        javaMailSender.setPassword("wx092846");
        javaMailSender.setHost("smtp.qq.com");

        from = javaMailSender.getUsername();

        String absolutePath = new ClassPathResource(userDataFilename).getFile().getAbsolutePath();
        String absolutePath1 = new ClassPathResource(dataFilename).getFile().getAbsolutePath();
        String absolutePath2 = new ClassPathResource(templateFilename).getFile().getAbsolutePath();
        sendMail(absolutePath, absolutePath1,outDataPath, absolutePath2,from,mailSubject,mailContent);
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
