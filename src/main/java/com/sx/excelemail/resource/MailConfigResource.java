package com.sx.excelemail.resource;

import com.sx.excelemail.po.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailConfigResource {

    @PostMapping("/config")
    public void mailConfig(MailConfig mailConfig){



    }
}
