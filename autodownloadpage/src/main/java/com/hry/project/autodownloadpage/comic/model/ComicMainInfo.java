package com.hry.project.autodownloadpage.comic.model;

import java.util.Date;

public class ComicMainInfo {
    private Integer id;

    private String name;

    private String imageAuthor;

    private String productor;

    private String introduction;

    private String indexImage;

    private Integer original;

    private String indexUrl;

    private Date createTime;

    private String charAuthor;

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

    public String getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(String imageAuthor) {
        this.imageAuthor = imageAuthor == null ? null : imageAuthor.trim();
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor == null ? null : productor.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage == null ? null : indexImage.trim();
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl == null ? null : indexUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCharAuthor() {
        return charAuthor;
    }

    public void setCharAuthor(String charAuthor) {
        this.charAuthor = charAuthor == null ? null : charAuthor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}