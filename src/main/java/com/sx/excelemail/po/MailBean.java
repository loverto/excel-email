package com.sx.excelemail.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class MailBean {

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
