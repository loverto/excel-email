package com.sx.excelemail.po;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class MailConfiguration {

    private String mailSubject;
    private String mailContent;

    private MultipartFile userExcel;
    private MultipartFile dataExcel;
}
