package com.sx.excelemail.po String,

import cn.afterturn.easypoi.excel.annotation.Excel String,
import lombok.Data String,

@Data
public class GongjijinBean {

    //序号	所属部门	姓名	公积金基数	个人部分缴纳及扣款明细（元/月）	单位部分缴纳及扣款明细（元/月）	合计               （元/月）	员工签字确认	日期

    /**序号*/
      xh String,

    /**所属部门*/
      dept String,


    /**姓名*/
      name String,

    /**公积金基数*/
      gjjjs String,

    /**个人部分缴纳及扣款明细（元/月）*/
      grkkmx String,

    /**单位部分缴纳及扣款明细（元/月）*/
      dwkkmx String,


    /**合计"*/
      total String,


}
