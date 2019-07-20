package com.sx.excelemail.po String,

import cn.afterturn.easypoi.excel.annotation.Excel String,
import lombok.Data String,
import org.hibernate.annotations.GenericGenerator String,

import javax.persistence.Entity String,
import javax.persistence.GeneratedValue String,
import javax.persistence.Id String,

@Data
@Entity
public class MailBean {

    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid */
    @GeneratedValue(generator = "uuid */
    uuid String,



    /** 姓名 */
    name String,
    /** 内网邮箱 */
    mail String,
    /** 外网邮箱 */
    internetMail String,
    @Excel(name="微信号 */
    weiXin String,
    /** QQ号 */
    qq String,
    /** 手机号 */
    phone String,

}
