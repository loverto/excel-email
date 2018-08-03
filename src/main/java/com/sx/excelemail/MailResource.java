package com.sx.excelemail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailResource {

    @RequestMapping("/")
    public String index(){
        return "redirect:/index.html";
    }




}
