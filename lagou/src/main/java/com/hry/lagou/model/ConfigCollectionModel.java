package com.hry.lagou.model;

import java.util.List;

/**
 * 查询配置
 */
public class ConfigCollectionModel {
    // collection_
    public static final String COLLECTION = "configCollectionModel";

    private String city; // 杭州
    private List<ConfigModel> configModelList;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ConfigModel> getConfigModelList() {
        return configModelList;
    }

    public void setConfigModelList(List<ConfigModel> configModelList) {
        this.configModelList = configModelList;
    }

    public static class ConfigModel{
        private String kd; // 关键字
        private Long lastQuery; // 最后执行日期
        private Integer pageNum; // 每天假设增加的页数

        public String getKd() {
            return kd;
        }

        public void setKd(String kd) {
            this.kd = kd;
        }

        public Long getLastQuery() {
            return lastQuery;
        }

        public void setLastQuery(Long lastQuery) {
            this.lastQuery = lastQuery;
        }

        public Integer getPageNum() {
            return pageNum;
        }

        public void setPageNum(Integer pageNum) {
            this.pageNum = pageNum;
        }
    }
}
