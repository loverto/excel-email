package com.sx.excelemail.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class MailBean {

    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String uuid;



    @Excel(name = "姓名")
    private String name;
    @Excel(name = "内网邮箱")
    private String mail;
    @Excel(name = "外网邮箱")
    private String internetMail;
    @Excel(name="微信号")
    private String weiXin;
    @Excel(name = "QQ号")
    private String qq;
    @Excel(name = "手机号")
    private String phone;

}
