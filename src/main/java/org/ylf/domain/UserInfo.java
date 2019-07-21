package org.ylf.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The UserInfo entity.
 * @author ylf
 */
@ApiModel(description = "The UserInfo entity. @author ylf")
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Column(name = "name")
    private String name;

    /**
     * 内网邮箱
     */
    @ApiModelProperty(value = "内网邮箱")
    @Column(name = "mail")
    private String mail;

    /**
     * 外网邮箱
     */
    @ApiModelProperty(value = "外网邮箱")
    @Column(name = "internet_mail")
    private String internetMail;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    @Column(name = "wei_xin")
    private String weiXin;

    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ号")
    @Column(name = "qq")
    private String qq;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Column(name = "id_card")
    private String idCard;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public UserInfo mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInternetMail() {
        return internetMail;
    }

    public UserInfo internetMail(String internetMail) {
        this.internetMail = internetMail;
        return this;
    }

    public void setInternetMail(String internetMail) {
        this.internetMail = internetMail;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public UserInfo weiXin(String weiXin) {
        this.weiXin = weiXin;
        return this;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getQq() {
        return qq;
    }

    public UserInfo qq(String qq) {
        this.qq = qq;
        return this;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public UserInfo phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public UserInfo idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        return id != null && id.equals(((UserInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mail='" + getMail() + "'" +
            ", internetMail='" + getInternetMail() + "'" +
            ", weiXin='" + getWeiXin() + "'" +
            ", qq='" + getQq() + "'" +
            ", phone='" + getPhone() + "'" +
            ", idCard='" + getIdCard() + "'" +
            "}";
    }
}
