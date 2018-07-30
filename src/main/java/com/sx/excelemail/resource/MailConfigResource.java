package com.sx.excelemail.resource;

import com.sx.excelemail.dao.MailConfigRepository;
import com.sx.excelemail.po.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
public class MailConfigResource {


    @Autowired
    JavaMailSender mailSender;

    @Autowired
    MailConfigRepository mailConfigRepository;

    @PostMapping("/config")
    public ResponseEntity<MailConfig> addMailConfig(@Validated MailConfig mailConfig){
            mailConfigRepository.save(mailConfig);
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
           return ResponseEntity.ok(mailConfig);
    }

    @GetMapping("/config")
    public ResponseEntity<MailConfig> getMailConfig(){
        MailConfig mailConfig = mailConfigRepository.findAll().get(0);
        return ResponseEntity.ok(mailConfig);
    }
}
