package com.hry.project.simple.csdnblog;

import com.alibaba.fastjson.JSON;

/**
 * Created by huangrongyou@yixin.im on 2018/4/2.
 */
public class ArticleBO {
    private String title; // 标题
    private String href; // 地址
    private int numberOfView; // 阅读量
    private String postDate; // 发布日期

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
