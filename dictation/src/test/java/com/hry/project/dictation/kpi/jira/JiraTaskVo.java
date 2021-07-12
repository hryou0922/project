package com.hry.project.dictation.kpi.jira;

import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/8 9:46
 */
public class JiraTaskVo {
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务url
     */
    private String url;
    /**
     * 任务拥有者
     */
    private String ower;
    /**
     * 花费总时间
     */
    private String totalCostTime;
    /**
     * 子任务列表
     */
    private List<SubTaskVo> subTaskVoList;

    public static class SubTaskVo{
        /**
         * 经办人
         */
        private String manager;
        /**
         * 花费时间
         */
        private String costTime;

        public SubTaskVo() {
        }

        public SubTaskVo(String manager, String costTime) {
            this.manager = manager;
            this.costTime = costTime;
        }

        public String getManager() {
            return manager;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getCostTime() {
            return costTime;
        }

        public void setCostTime(String costTime) {
            this.costTime = costTime;
        }
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwer() {
        return ower;
    }

    public void setOwer(String ower) {
        this.ower = ower;
    }

    public String getTotalCostTime() {
        return totalCostTime;
    }

    public void setTotalCostTime(String totalCostTime) {
        this.totalCostTime = totalCostTime;
    }

    public List<SubTaskVo> getSubTaskVoList() {
        return subTaskVoList;
    }

    public void setSubTaskVoList(List<SubTaskVo> subTaskVoList) {
        this.subTaskVoList = subTaskVoList;
    }
}

