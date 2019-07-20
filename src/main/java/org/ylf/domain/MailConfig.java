package org.ylf.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The MailConfig entity.
 * @author ylf
 */
@ApiModel(description = "The MailConfig entity. @author ylf")
@Entity
@Table(name = "mail_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MailConfig extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 邮件用户名
     */
    @ApiModelProperty(value = "邮件用户名")
    @Column(name = "username")
    private String username;

    /**
     * 邮件密码
     */
    @ApiModelProperty(value = "邮件密码")
    @Column(name = "password")
    private String password;

    /**
     * smtp服务器ip
     */
    @ApiModelProperty(value = "smtp服务器ip")
    @Column(name = "smtp_server")
    private String smtpServer;

    /**
     * smtp服务器端口
     */
    @ApiModelProperty(value = "smtp服务器端口")
    @Column(name = "smtp_port")
    private Integer smtpPort;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @Column(name = "type_id")
    private String typeId;

    /**
     * 邮件类型
     */
    @ApiModelProperty(value = "邮件类型")
    @Column(name = "mail_type")
    private String mailType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public MailConfig username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public MailConfig password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public MailConfig smtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
        return this;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public MailConfig smtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
        return this;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getTypeId() {
        return typeId;
    }

    public MailConfig typeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getMailType() {
        return mailType;
    }

    public MailConfig mailType(String mailType) {
        this.mailType = mailType;
        return this;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailConfig)) {
            return false;
        }
        return id != null && id.equals(((MailConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MailConfig{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", smtpServer='" + getSmtpServer() + "'" +
            ", smtpPort=" + getSmtpPort() +
            ", typeId='" + getTypeId() + "'" +
            ", mailType='" + getMailType() + "'" +
            "}";
    }
}
