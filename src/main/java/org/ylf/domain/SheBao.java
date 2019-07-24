package org.ylf.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The SheBao entity.
 * @author ylf
 */
@ApiModel(description = "The SheBao entity. @author ylf")
@Entity
@Table(name = "she_bao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SheBao extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 北控三兴员工社会保险基数确认单_序号
     */
    @ApiModelProperty(value = "北控三兴员工社会保险基数确认单_序号")
    @Column(name = "xh")
    private Integer xh;

    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    @Column(name = "dept")
    private String dept;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Column(name = "name")
    private String name;

    /**
     * 社保基数_养/失
     */
    @ApiModelProperty(value = "社保基数_养/失")
    @Column(name = "sbjs_yanglao_shiye")
    private String sbjsYanglaoShiye;

    /**
     * 工伤
     */
    @ApiModelProperty(value = "工伤")
    @Column(name = "sbjs_gong_shang")
    private String sbjsGongShang;

    /**
     * 医/伤/生
     */
    @ApiModelProperty(value = "医/伤/生")
    @Column(name = "sbjs_yi_shang_sheng")
    private String sbjsYiShangSheng;

    /**
     * 个人部分缴纳及扣款明细（元/月）_养老
     */
    @ApiModelProperty(value = "个人部分缴纳及扣款明细（元/月）_养老")
    @Column(name = "grkkxx_yaolang")
    private String grkkxxYaolang;

    /**
     * 失业
     */
    @ApiModelProperty(value = "失业")
    @Column(name = "grkkxx_shiye")
    private String grkkxxShiye;

    /**
     * 医疗
     */
    @ApiModelProperty(value = "医疗")
    @Column(name = "grkkxx_yiliao")
    private String grkkxxYiliao;

    /**
     * 个人合计
     */
    @ApiModelProperty(value = "个人合计")
    @Column(name = "grkkxx_total")
    private String grkkxxTotal;

    /**
     * 单位部分缴纳及扣款明细（元/月）_养老
     */
    @ApiModelProperty(value = "单位部分缴纳及扣款明细（元/月）_养老")
    @Column(name = "dwbf_yaolao")
    private String dwbfYaolao;

    /**
     * 失业
     */
    @ApiModelProperty(value = "失业")
    @Column(name = "dwbf_shiye")
    private String dwbfShiye;

    /**
     * 医疗
     */
    @ApiModelProperty(value = "医疗")
    @Column(name = "dwbf_yiliao")
    private String dwbfYiliao;

    /**
     * 工伤
     */
    @ApiModelProperty(value = "工伤")
    @Column(name = "dwbf_gongshang")
    private String dwbfGongshang;

    /**
     * 生育
     */
    @ApiModelProperty(value = "生育")
    @Column(name = "dwbf_shengyu")
    private String dwbfShengyu;

    /**
     * 单位合计
     */
    @ApiModelProperty(value = "单位合计")
    @Column(name = "dwbf_total")
    private String dwbfTotal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getXh() {
        return xh;
    }

    public SheBao xh(Integer xh) {
        this.xh = xh;
        return this;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getDept() {
        return dept;
    }

    public SheBao dept(String dept) {
        this.dept = dept;
        return this;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public SheBao name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSbjsYanglaoShiye() {
        return sbjsYanglaoShiye;
    }

    public SheBao sbjsYanglaoShiye(String sbjsYanglaoShiye) {
        this.sbjsYanglaoShiye = sbjsYanglaoShiye;
        return this;
    }

    public void setSbjsYanglaoShiye(String sbjsYanglaoShiye) {
        this.sbjsYanglaoShiye = sbjsYanglaoShiye;
    }

    public String getSbjsGongShang() {
        return sbjsGongShang;
    }

    public SheBao sbjsGongShang(String sbjsGongShang) {
        this.sbjsGongShang = sbjsGongShang;
        return this;
    }

    public void setSbjsGongShang(String sbjsGongShang) {
        this.sbjsGongShang = sbjsGongShang;
    }

    public String getSbjsYiShangSheng() {
        return sbjsYiShangSheng;
    }

    public SheBao sbjsYiShangSheng(String sbjsYiShangSheng) {
        this.sbjsYiShangSheng = sbjsYiShangSheng;
        return this;
    }

    public void setSbjsYiShangSheng(String sbjsYiShangSheng) {
        this.sbjsYiShangSheng = sbjsYiShangSheng;
    }

    public String getGrkkxxYaolang() {
        return grkkxxYaolang;
    }

    public SheBao grkkxxYaolang(String grkkxxYaolang) {
        this.grkkxxYaolang = grkkxxYaolang;
        return this;
    }

    public void setGrkkxxYaolang(String grkkxxYaolang) {
        this.grkkxxYaolang = grkkxxYaolang;
    }

    public String getGrkkxxShiye() {
        return grkkxxShiye;
    }

    public SheBao grkkxxShiye(String grkkxxShiye) {
        this.grkkxxShiye = grkkxxShiye;
        return this;
    }

    public void setGrkkxxShiye(String grkkxxShiye) {
        this.grkkxxShiye = grkkxxShiye;
    }

    public String getGrkkxxYiliao() {
        return grkkxxYiliao;
    }

    public SheBao grkkxxYiliao(String grkkxxYiliao) {
        this.grkkxxYiliao = grkkxxYiliao;
        return this;
    }

    public void setGrkkxxYiliao(String grkkxxYiliao) {
        this.grkkxxYiliao = grkkxxYiliao;
    }

    public String getGrkkxxTotal() {
        return grkkxxTotal;
    }

    public SheBao grkkxxTotal(String grkkxxTotal) {
        this.grkkxxTotal = grkkxxTotal;
        return this;
    }

    public void setGrkkxxTotal(String grkkxxTotal) {
        this.grkkxxTotal = grkkxxTotal;
    }

    public String getDwbfYaolao() {
        return dwbfYaolao;
    }

    public SheBao dwbfYaolao(String dwbfYaolao) {
        this.dwbfYaolao = dwbfYaolao;
        return this;
    }

    public void setDwbfYaolao(String dwbfYaolao) {
        this.dwbfYaolao = dwbfYaolao;
    }

    public String getDwbfShiye() {
        return dwbfShiye;
    }

    public SheBao dwbfShiye(String dwbfShiye) {
        this.dwbfShiye = dwbfShiye;
        return this;
    }

    public void setDwbfShiye(String dwbfShiye) {
        this.dwbfShiye = dwbfShiye;
    }

    public String getDwbfYiliao() {
        return dwbfYiliao;
    }

    public SheBao dwbfYiliao(String dwbfYiliao) {
        this.dwbfYiliao = dwbfYiliao;
        return this;
    }

    public void setDwbfYiliao(String dwbfYiliao) {
        this.dwbfYiliao = dwbfYiliao;
    }

    public String getDwbfGongshang() {
        return dwbfGongshang;
    }

    public SheBao dwbfGongshang(String dwbfGongshang) {
        this.dwbfGongshang = dwbfGongshang;
        return this;
    }

    public void setDwbfGongshang(String dwbfGongshang) {
        this.dwbfGongshang = dwbfGongshang;
    }

    public String getDwbfShengyu() {
        return dwbfShengyu;
    }

    public SheBao dwbfShengyu(String dwbfShengyu) {
        this.dwbfShengyu = dwbfShengyu;
        return this;
    }

    public void setDwbfShengyu(String dwbfShengyu) {
        this.dwbfShengyu = dwbfShengyu;
    }

    public String getDwbfTotal() {
        return dwbfTotal;
    }

    public SheBao dwbfTotal(String dwbfTotal) {
        this.dwbfTotal = dwbfTotal;
        return this;
    }

    public void setDwbfTotal(String dwbfTotal) {
        this.dwbfTotal = dwbfTotal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SheBao)) {
            return false;
        }
        return id != null && id.equals(((SheBao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SheBao{" +
            "id=" + getId() +
            ", xh=" + getXh() +
            ", dept='" + getDept() + "'" +
            ", name='" + getName() + "'" +
            ", sbjsYanglaoShiye='" + getSbjsYanglaoShiye() + "'" +
            ", sbjsGongShang='" + getSbjsGongShang() + "'" +
            ", sbjsYiShangSheng='" + getSbjsYiShangSheng() + "'" +
            ", grkkxxYaolang='" + getGrkkxxYaolang() + "'" +
            ", grkkxxShiye='" + getGrkkxxShiye() + "'" +
            ", grkkxxYiliao='" + getGrkkxxYiliao() + "'" +
            ", grkkxxTotal='" + getGrkkxxTotal() + "'" +
            ", dwbfYaolao='" + getDwbfYaolao() + "'" +
            ", dwbfShiye='" + getDwbfShiye() + "'" +
            ", dwbfYiliao='" + getDwbfYiliao() + "'" +
            ", dwbfGongshang='" + getDwbfGongshang() + "'" +
            ", dwbfShengyu='" + getDwbfShengyu() + "'" +
            ", dwbfTotal='" + getDwbfTotal() + "'" +
            "}";
    }
}
