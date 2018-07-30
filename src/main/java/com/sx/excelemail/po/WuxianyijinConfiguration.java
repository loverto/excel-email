package com.sx.excelemail.po;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class WuxianyijinConfiguration {

    private String username;
    private String password;
    private String smtpServer;
    private int smtpPort;

    private MultipartFile userExcel;
    private MultipartFile dataExcel;
}
