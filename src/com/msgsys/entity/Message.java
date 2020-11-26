package com.msgsys.entity;

import java.sql.Timestamp;

/**
 * 作者：LiuYunTao
 * 日期: 15:37 2020/11/26
 * 描述：
 */
public class Message {
    private Integer id;
    private Integer formUid;
    private Integer toUid;
    private String mTitle;
    private String mContent;
    private Integer readFlag;
    private String createTime;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", formUid=" + formUid +
                ", toUid=" + toUid +
                ", mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                ", readFlag=" + readFlag +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFormUid() {
        return formUid;
    }

    public void setFormUid(Integer formUid) {
        this.formUid = formUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
