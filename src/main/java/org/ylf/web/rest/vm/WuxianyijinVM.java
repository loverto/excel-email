package org.ylf.web.rest.vm;

public class WuxianyijinVM{
    private long mailConfigId;
    private long mailContentId;

    @Override
    public String toString() {
        return "WuxianyijinVM{" +
            "mailConfigId=" + mailConfigId +
            ", mailContentId=" + mailContentId +
            '}';
    }

    public long getMailConfigId() {
        return mailConfigId;
    }

    public void setMailConfigId(final long mailConfigId) {
        this.mailConfigId = mailConfigId;
    }

    public long getMailContentId() {
        return mailContentId;
    }

    public void setMailContentId(final long mailContentId) {
        this.mailContentId = mailContentId;
    }
}
