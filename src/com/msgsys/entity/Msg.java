package com.msgsys.entity;

import java.sql.Timestamp;

/**
 * 作者：LiuYunTao
 * 日期: 15:37 2020/11/26
 * 描述：
 */
public class Msg {
    private Integer id;
    private Integer formUid;
    private Integer toUid;
    private String mtitle;
    private String mcontent;
    private String createTime;

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", formUid=" + formUid +
                ", toUid=" + toUid +
                ", mtitle='" + mtitle + '\'' +
                ", mcontent='" + mcontent + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Msg() {
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

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
