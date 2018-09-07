package com.hry.lagou.lagou.position;

public class PositionResult {

    private Integer totalCount;  // "totalCount": 361,
    private Integer resultSize; //	"resultSize": 15,;
    private String hotLabels; //"hotLabels": null,
    private String hiTags; //        "hiTags": null,

    private LocationInfo locationInfo;
    private QueryAnalysisInfo queryAnalysisInfo;
    private StrategyProperty strategyProperty;
    private SinglePosition[] result;

    /**
     *
     * "locationInfo": {
     "city": "杭州",
     "district": null,
     "businessZone": null,
     "locationCode": null,
     "isAllhotBusinessZone": false,
     "queryByGisCode": false
     },
     */
    public static class LocationInfo{
        private String city;
        private String district;
        private String businessZone;
        private String locationCode;
        private Boolean isAllhotBusinessZone;
        private Boolean queryByGisCode;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getBusinessZone() {
            return businessZone;
        }

        public void setBusinessZone(String businessZone) {
            this.businessZone = businessZone;
        }

        public String getLocationCode() {
            return locationCode;
        }

        public void setLocationCode(String locationCode) {
            this.locationCode = locationCode;
        }

        public Boolean getAllhotBusinessZone() {
            return isAllhotBusinessZone;
        }

        public void setAllhotBusinessZone(Boolean allhotBusinessZone) {
            isAllhotBusinessZone = allhotBusinessZone;
        }

        public Boolean getQueryByGisCode() {
            return queryByGisCode;
        }

        public void setQueryByGisCode(Boolean queryByGisCode) {
            this.queryByGisCode = queryByGisCode;
        }
    }

    /**
     * "queryAnalysisInfo": {
     "positionName": "架构师",
     "companyName": null,
     "jobNature": null,
     "industryName": null,
     "usefulCompany": false
     },
     */
    public static class QueryAnalysisInfo{
        private String positionName;
        private String companyName;
        private String jobNature;
        private String industryName;
        private Boolean usefulCompany;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getJobNature() {
            return jobNature;
        }

        public void setJobNature(String jobNature) {
            this.jobNature = jobNature;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public Boolean getUsefulCompany() {
            return usefulCompany;
        }

        public void setUsefulCompany(Boolean usefulCompany) {
            this.usefulCompany = usefulCompany;
        }
    }

    /**
     * "strategyProperty": {
     "name": "dm-csearch-useUserAllInterest",
     "id": 0
     },
     */
    public static class StrategyProperty{
        private String name;
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getResultSize() {
        return resultSize;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }

    public String getHotLabels() {
        return hotLabels;
    }

    public void setHotLabels(String hotLabels) {
        this.hotLabels = hotLabels;
    }

    public String getHiTags() {
        return hiTags;
    }

    public void setHiTags(String hiTags) {
        this.hiTags = hiTags;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public QueryAnalysisInfo getQueryAnalysisInfo() {
        return queryAnalysisInfo;
    }

    public void setQueryAnalysisInfo(QueryAnalysisInfo queryAnalysisInfo) {
        this.queryAnalysisInfo = queryAnalysisInfo;
    }

    public StrategyProperty getStrategyProperty() {
        return strategyProperty;
    }

    public void setStrategyProperty(StrategyProperty strategyProperty) {
        this.strategyProperty = strategyProperty;
    }

    public SinglePosition[] getResult() {
        return result;
    }

    public void setResult(SinglePosition[] result) {
        this.result = result;
    }
}
