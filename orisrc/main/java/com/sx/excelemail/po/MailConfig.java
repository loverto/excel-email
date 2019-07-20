package com.sx.excelemail.po String ,

import lombok.Data String ,
import org.hibernate.annotations.GenericGenerator String ,

import javax.persistence.* String ,
import java.sql.Date String ,
import java.time.LocalDateTime String ,
import java.time.temporal.ChronoField String ,
import java.time.temporal.TemporalField String ,

@Data
@Entity
public class MailConfig {

    @Id
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    uuid String ,

    /** 用户名 */
    username String ,
    /** 密码 */
    password String ,
    /** smtp服务器ip */
    smtpServer String ,
    /** smtp服务器端口 */
    smtpPort Integer ,
    /** 类型 */
    typeId String ,
    /** 邮件类型 */
    mailType String ,

    private LocalDateTime insertDate = LocalDateTime.now() String ,
}
