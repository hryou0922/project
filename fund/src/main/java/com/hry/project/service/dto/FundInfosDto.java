package com.hry.project.service.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * 基金信息
 */
public class FundInfosDto {
    private String name;
    /**
     * 基金编码
     */
    private String fundCode;

    /**
     * 收益的类型：1年，3年，5年
     */
    private String type;

    private List<FundInfoDto> fundInfoDtoList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FundInfoDto> getFundInfoDtoList() {
        return fundInfoDtoList;
    }

    public void setFundInfoDtoList(List<FundInfoDto> fundInfoDtoList) {
        this.fundInfoDtoList = fundInfoDtoList;
    }

    public static final class FundInfoDto{
        /**
         * 时间
         */
        @JSONField(format="yyyy-MM-dd")
        private Date date;
        /**
         * 累计收益率
         */
        private double cumulativeRate;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getCumulativeRate() {
            return cumulativeRate;
        }

        public void setCumulativeRate(double cumulativeRate) {
            this.cumulativeRate = cumulativeRate;
        }
    }
}
