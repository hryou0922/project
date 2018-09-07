package com.hry.lagou.model;

import java.util.Set;

public class HRInfoModel {
    // collection_
    public static final String COLLECTION = "hrInfo";

    private Integer userId;
    private String phone;
    private String positionName;
    private String receiveEmail;
    private String realName;
    private String portrait;
    private String userLevel;
    private Boolean canTalk;
    private Set<String> positionIdSet;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Boolean getCanTalk() {
        return canTalk;
    }

    public void setCanTalk(Boolean canTalk) {
        this.canTalk = canTalk;
    }

    public Set<String> getPositionIdSet() {
        return positionIdSet;
    }

    public void setPositionIdSet(Set<String> positionIdSet) {
        this.positionIdSet = positionIdSet;
    }
}
