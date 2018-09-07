package com.hry.lagou.model;

/**
 * 下载目录的信息
 */
public class DownloadSaveInfoModel {
    // collection_
    public static final String COLLECTION = "downloadSaveInfoModel";

    private String kd; // 查询条件
    private String dir; // 目录

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }
}
