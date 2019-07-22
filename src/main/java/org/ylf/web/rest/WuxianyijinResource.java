package org.ylf.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ylf.domain.MailConfig;
import org.ylf.domain.MailContent;
import org.ylf.domain.UserInfo;
import org.ylf.repository.MailConfigRepository;
import org.ylf.repository.MailContentRepository;
import org.ylf.repository.UserInfoRepository;
import org.ylf.service.MailUtilService;
import org.ylf.web.rest.vm.WuxianyijinVM;

@RestController
@RequestMapping("/api")
public class WuxianyijinResource {

    private final Logger log = LoggerFactory.getLogger(WuxianyijinResource.class);

    private final MailUtilService mailUtilService;
    private final JavaMailSender mailSender;
    private final Environment environment;
    private final MailConfigRepository mailConfigRepository;
    private final MailContentRepository mailContentRepository;
    private final UserInfoRepository userInfoRepository;



    public WuxianyijinResource(MailUtilService mailUtilService,JavaMailSender mailSender,Environment environment,MailConfigRepository mailConfigRepository,MailContentRepository mailContentRepository,UserInfoRepository userInfoRepository){
        this.environment = environment;
        this.mailUtilService = mailUtilService;
        this.mailSender = mailSender;
        this.mailConfigRepository = mailConfigRepository;
        this.mailContentRepository = mailContentRepository;
        this.userInfoRepository = userInfoRepository;

    }

    /**
     * 要处理的数据文件
     */
    private  String templateUrl = "d:/upload";
    private  String templateFilename = "template.xlsx";
    private  String outDataPath = "D:/excel/";
    private String from = "yinlongfei@aliyun.com";
    private String mailSubject = "测试公积金";
    private String mailContent = "测试公积金";


    @PostConstruct
    public void init(){
        templateUrl = environment.getProperty("application.template.path",templateUrl);
        templateFilename = environment.getProperty("application.template.filename",templateFilename);
        outDataPath = environment.getProperty("application.out.data.path",outDataPath);

        from = environment.getProperty("application.mail.from",from);
        mailSubject = environment.getProperty("application.mail.subject",mailSubject);
        mailContent = environment.getProperty("application.mail.content",mailContent);

    }



    @PostMapping("/wxyj")
    public ResponseEntity<String> wuxianyijinConfiguration(@RequestBody WuxianyijinVM wuxianyijinVM) throws Exception{

        try {

            Optional<MailConfig> mailConfig1 = mailConfigRepository.findById(wuxianyijinVM.getMailConfigId());
            MailConfig mailConfig = mailConfig1.get();
            //邮箱设置
            if (StringUtils.isNotBlank(mailConfig.getPassword())
                &&StringUtils.isNotBlank(mailConfig.getSmtpServer())
                &&StringUtils.isNotBlank(mailConfig.getUsername())
            ){
                JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) mailSender;
                javaMailSender.setHost(mailConfig.getSmtpServer());
                javaMailSender.setPassword(mailConfig.getPassword());
                javaMailSender.setPort(mailConfig.getSmtpPort());
                javaMailSender.setUsername(mailConfig.getUsername());
            }
            from = ((JavaMailSenderImpl)mailSender).getUsername();
            Optional<MailContent> mailContentOptional = mailContentRepository.findById(wuxianyijinVM.getMailContentId());
            MailContent mc = mailContentOptional.get();
            mailSubject = mc.getMailSubject();
            mailContent = mc.getMailContent();
            mailUtilService.sendMail(outDataPath,templateUrl+File.separator+templateFilename,from,mailSubject, this.mailContent);
            return ResponseEntity.ok("success");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("fail");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("fail");
        }
    }

}
