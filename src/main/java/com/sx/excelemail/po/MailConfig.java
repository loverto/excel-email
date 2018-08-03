package com.sx.excelemail.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

@Data
@Entity
public class MailConfig {

    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String uuid;

    private String username;
    private String password;
    private String smtpServer;
    private int smtpPort;

    private Date insertDate = new Date(new java.util.Date().getTime());
}
