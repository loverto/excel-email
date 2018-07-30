package com.sx.excelemail.po;

import lombok.Data;

@Data
public class MailConfig {

    private String username;
    private String password;
    private String smtpServer;
    private int smtpPort;
}
