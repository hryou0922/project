package com.hry.project.autodownloadpage.comic.model;

import java.util.Date;

public class ComicAuthor {
    private Integer id;

    private String name;

    private String authNotice;

    private String type;

    private Integer numOfWork;

    private Date createTime;

    private String authorImage;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthNotice() {
        return authNotice;
    }

    public void setAuthNotice(String authNotice) {
        this.authNotice = authNotice == null ? null : authNotice.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getNumOfWork() {
        return numOfWork;
    }

    public void setNumOfWork(Integer numOfWork) {
        this.numOfWork = numOfWork;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage == null ? null : authorImage.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}