package org.ylf.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The GongjijinHistory entity.
 * @author ylf
 */
@ApiModel(description = "The GongjijinHistory entity. @author ylf")
@Entity
@Table(name = "gongjijin_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GongjijinHistory extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @Column(name = "xh")
    private String xh;

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
     * 公积金基数
     */
    @ApiModelProperty(value = "公积金基数")
    @Column(name = "gjjjs")
    private String gjjjs;

    /**
     * 个人部分缴纳及扣款明细（元/月）
     */
    @ApiModelProperty(value = "个人部分缴纳及扣款明细（元/月）")
    @Column(name = "grkkmx")
    private String grkkmx;

    /**
     * 单位部分缴纳及扣款明细（元/月）
     */
    @ApiModelProperty(value = "单位部分缴纳及扣款明细（元/月）")
    @Column(name = "dwkkmx")
    private String dwkkmx;

    /**
     * 合计\"
     */
    @ApiModelProperty(value = "合计\"")
    @Column(name = "total")
    private String total;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public GongjijinHistory xh(String xh) {
        this.xh = xh;
        return this;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getDept() {
        return dept;
    }

    public GongjijinHistory dept(String dept) {
        this.dept = dept;
        return this;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public GongjijinHistory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGjjjs() {
        return gjjjs;
    }

    public GongjijinHistory gjjjs(String gjjjs) {
        this.gjjjs = gjjjs;
        return this;
    }

    public void setGjjjs(String gjjjs) {
        this.gjjjs = gjjjs;
    }

    public String getGrkkmx() {
        return grkkmx;
    }

    public GongjijinHistory grkkmx(String grkkmx) {
        this.grkkmx = grkkmx;
        return this;
    }

    public void setGrkkmx(String grkkmx) {
        this.grkkmx = grkkmx;
    }

    public String getDwkkmx() {
        return dwkkmx;
    }

    public GongjijinHistory dwkkmx(String dwkkmx) {
        this.dwkkmx = dwkkmx;
        return this;
    }

    public void setDwkkmx(String dwkkmx) {
        this.dwkkmx = dwkkmx;
    }

    public String getTotal() {
        return total;
    }

    public GongjijinHistory total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GongjijinHistory)) {
            return false;
        }
        return id != null && id.equals(((GongjijinHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GongjijinHistory{" +
            "id=" + getId() +
            ", xh='" + getXh() + "'" +
            ", dept='" + getDept() + "'" +
            ", name='" + getName() + "'" +
            ", gjjjs='" + getGjjjs() + "'" +
            ", grkkmx='" + getGrkkmx() + "'" +
            ", dwkkmx='" + getDwkkmx() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}
