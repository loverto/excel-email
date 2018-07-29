package com.sx.excelemail.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ShebaoBean {

    //序号	所属部门	姓名	社保基数		个人部分缴纳及扣款明细（元/月）				单位部分缴纳及扣款明细（元/月）						员工签字确认	日期
    //                              养/失	医/伤/生	养老	失业	医疗	个人合计	养老	失业	医疗	工伤	生育	单位合计

    @Excel(name = "北控三兴2018年7月至2019年6月员工社会保险基数确认单_序号")
    private int xh;

    @Excel(name = "所属部门")
    private String dept;


    @Excel(name = "姓名")
    private String name;


    @Excel(name = "社保基数_养/失")
    private String sbjsYanglaoShiye;


    @Excel(name = "医/伤/生")
    private String sbjsYiShangSheng;

    @Excel(name = "个人部分缴纳及扣款明细（元/月）_养老")
    private String grkkxxYaolang;

    @Excel(name = "失业",fixedIndex = 6)
    private String grkkxxShiye;

    @Excel(name = "医疗",fixedIndex = 7)
    private String grkkxxYiliao;

    @Excel(name = "个人合计",fixedIndex = 8)
    private String grkkxxTotal;


    @Excel(name = "单位部分缴纳及扣款明细（元/月）_养老",fixedIndex = 9)
    private String dwbfYaolao;

    @Excel(name ="失业")
    private String dwbfShiye;

    @Excel(name = "医疗")
    private String dwbfYiliao;


    @Excel(name = "工伤")
    private String dwbfGongshang;


    @Excel(name = "生育")
    private String dwbfShengyu;


    @Excel(name = "单位合计")
    private String dwbfTotal;

}
