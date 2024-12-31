package org.example.imooc_oa.Entity;

import java.util.Date;

public class Notice {
    private long noticeId;
    private long receiverId;
    private String content;
    private Date createTime;

    public Notice(long receiverId, String content, Date createTime) {
        this.receiverId = receiverId;
        this.content = content;
        this.createTime = createTime;
    }

    public Notice() {
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
