package org.ylf.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * The MailContent entity.
 * @author ylf
 */
@ApiModel(description = "The MailContent entity. @author ylf")
@Entity
@Table(name = "mail_content")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MailContent extends AbstractAuditingEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    @Column(name = "mail_subject")
    private String mailSubject;

    /**
     * 邮件内容
     */
    @ApiModelProperty(value = "邮件内容")
    @Column(name = "mail_content")
    private String mailContent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public MailContent mailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
        return this;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public MailContent mailContent(String mailContent) {
        this.mailContent = mailContent;
        return this;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailContent)) {
            return false;
        }
        return id != null && id.equals(((MailContent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MailContent{" +
            "id=" + getId() +
            ", mailSubject='" + getMailSubject() + "'" +
            ", mailContent='" + getMailContent() + "'" +
            "}";
    }
}
