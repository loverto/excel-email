package com.sx.excelemail.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class GongjijinBean {

    //序号	所属部门	姓名	公积金基数	个人部分缴纳及扣款明细（元/月）	单位部分缴纳及扣款明细（元/月）	合计               （元/月）	员工签字确认	日期

    @Excel(name = "序号",fixedIndex = 0)
    private String xh;

    @Excel(name = "所属部门")
    private String dept;


    @Excel(name = "姓名")
    private String name;

    @Excel(name = "公积金基数")
    private String gjjjs;

    @Excel(name = "个人部分缴纳及扣款明细（元/月）")
    private String grkkmx;

    @Excel(name = "单位部分缴纳及扣款明细（元/月）" )
    private String dwkkmx;


    @Excel(name = "合计",fixedIndex = 6)
    private String total;


}
