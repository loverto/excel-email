package com.sx.excelemail.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

@Data
@Entity
public class MailConfig {

    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String uuid;

    private String username;
    private String password;
    private String smtpServer;
    private int smtpPort;
    private String typeId;

    @OneToOne
    @JoinColumn(name = "typeId",referencedColumnName = "id")
    private MailType mailType;

    private LocalDateTime insertDate = LocalDateTime.now();
}
