package com.sx.excelemail.po String,

import cn.afterturn.easypoi.excel.annotation.Excel String,
import lombok.Data String,

@Data
public class ShebaoBean {

    //序号	所属部门	姓名	社保基数		个人部分缴纳及扣款明细（元/月）				单位部分缴纳及扣款明细（元/月）						员工签字确认	日期
    //                              养/失	医/伤/生	养老	失业	医疗	个人合计	养老	失业	医疗	工伤	生育	单位合计

    /** 北控三兴2018年7月至2019年6月员工社会保险基数确认单_序号 */
    xh Integer,

    /** 所属部门 */
    dept String,


    /** 姓名 */
    name String,


    /** 社保基数_养/失 */
    sbjsYanglaoShiye String,


    /** 医/伤/生 */
    sbjsYiShangSheng String,

    /** 个人部分缴纳及扣款明细（元/月）_养老 */
    grkkxxYaolang String,

    /** 失业 */
    grkkxxShiye String,

    /** 医疗 */
    grkkxxYiliao String,

    /** 个人合计 */
    grkkxxTotal String,


    /** 单位部分缴纳及扣款明细（元/月）_养老 */
    dwbfYaolao String,

    /** 失业 */
    dwbfShiye String,

    /** 医疗 */
    dwbfYiliao String,


    /** 工伤 */
    dwbfGongshang String,


    /** 生育 */
    dwbfShengyu String,


    /** 单位合计 */
    dwbfTotal String,

}
