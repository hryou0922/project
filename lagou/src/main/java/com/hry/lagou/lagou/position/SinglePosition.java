package com.hry.lagou.lagou.position;

import java.util.Date;
import java.util.List;

/**
 * 职位信息
 *
 "companyId": 396566,
 "companyShortName": "创联科技",
 "positionId": 4913671,
 "industryField": "信息安全,硬件",
 "education": "本科",
 "workYear": "不限",
 "city": "杭州",
 "positionAdvantage": "15薪",
 "createTime": "2018-08-23 14:21:44",
 "salary": "20k-25k",
 "positionName": "架构师",
 "companySize": "150-500人",
 "companyLogo": "i/image2/M01/58/73/CgoB5lsiDySAGXsQAAA1KrSy4YM677.png",
 "financeStage": "上市公司",
 "jobNature": "全职",
 "approve": 0,
 "district": "西湖区",
 "companyLabelList": ["\"\""],
 "publisherId": 3463443,
 "score": 0,
 "positionLables": ["系统架构"],
 "industryLables": [],
 "businessZones": ["文三路"],
 "longitude": "120.126251",
 "latitude": "30.27346",
 "formatCreateTime": "3天前发布",
 "companyFullName": "高新兴创联科技有限公司",
 "adWord": 0,
 "resumeProcessRate": 100,
 "resumeProcessDay": 1,
 "imState": "threeDays",
 "lastLogin": 1535099244000,
 "explain": null,
 "plus": null,
 "pcShow": 0,
 "appShow": 0,
 "deliver": 0,
 "gradeDescription": null,
 "promotionScoreExplain": null,
 "firstType": "开发|测试|运维类",
 "secondType": "高端技术职位",
 "isSchoolJob": 0,
 "subwayline": "2号线",
 "stationname": "古翠路",
 "linestaion": "2号线_学院路;2号线_古翠路",
 "hitags": null
 */
public class SinglePosition {
    private Integer companyId; // "companyId": 396566,
    private String companyShortName; // "companyShortName": "创联科技",
    private Integer positionId; //                       "positionId": 4913671,
    private String industryField; //                        "industryField": "信息安全,硬件",
    private String education; //                       "education": "本科",
    private String workYear; //                       "workYear": "不限",
    private String city; //                      "city": "杭州",
    private String positionAdvantage; //                       "positionAdvantage": "15薪",
    private Date createTime; //                        "createTime": "2018-08-23 14:21:44",
    private String salary; //                      "salary": "20k-25k",
    private String positionName; //                       "positionName": "架构师",
    private String companySize; //                       "companySize": "150-500人",
    private String companyLogo; //  "companyLogo": "i/image2/M01/58/73/CgoB5lsiDySAGXsQAAA1KrSy4YM677.png",
    private String financeStage; //                       "financeStage": "上市公司",
    private String jobNature; //                       "jobNature": "全职",
    private Integer approve; //                       "approve": 0,
    private String district; //                       "district": "西湖区",
    private List<String> companyLabelList;//     "companyLabelList": ["\"\""],
    private Integer publisherId; //                        "publisherId": 3463443,
    private Integer score; //                       "score": 0,
    private List<String> positionLables; //                        "positionLables": ["系统架构"],
    private List<String> industryLables; //                        "industryLables": [],
    private List<String> businessZones; //                        "businessZones": ["文三路"],
    private String longitude; //                      "longitude": "120.126251",
    private String latitude; //                       "latitude": "30.27346",
    private String formatCreateTime; //                       "formatCreateTime": "3天前发布",
    private String companyFullName; //                       "companyFullName": "高新兴创联科技有限公司",
    private Integer adWord;//                        "adWord": 0,
    private Integer resumeProcessRate; //                        "resumeProcessRate": 100,
    private Integer resumeProcessDay; //                        "resumeProcessDay": 1,
    private String  imState; //                      "imState": "threeDays",
    private Long lastLogin; //                       "lastLogin": 1535099244000,
    private String explain; //                        "explain": null,
    private String plus; //                       "plus": null,
    private Integer pcShow; //                        "pcShow": 0,
    private Integer appShow; //                        "appShow": 0,
    private Integer deliver; //                        "deliver": 0,
    private String gradeDescription; //                       "gradeDescription": null,
    private String promotionScoreExplain; //                       "promotionScoreExplain": null,
    private String firstType; //                       "firstType": "开发|测试|运维类",
    private String secondType; //                        "secondType": "高端技术职位",
    private Integer isSchoolJob; //                        "isSchoolJob": 0,
    private String subwayline; //                       "subwayline": "2号线",
    private String stationname; //                       "stationname": "古翠路",
    private String linestaion; //                       "linestaion": "2号线_学院路;2号线_古翠路",
    private String hitags; //                       "hitags": null

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPositionAdvantage() {
        return positionAdvantage;
    }

    public void setPositionAdvantage(String positionAdvantage) {
        this.positionAdvantage = positionAdvantage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getFinanceStage() {
        return financeStage;
    }

    public void setFinanceStage(String financeStage) {
        this.financeStage = financeStage;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public Integer getApprove() {
        return approve;
    }

    public void setApprove(Integer approve) {
        this.approve = approve;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<String> getCompanyLabelList() {
        return companyLabelList;
    }

    public void setCompanyLabelList(List<String> companyLabelList) {
        this.companyLabelList = companyLabelList;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getPositionLables() {
        return positionLables;
    }

    public void setPositionLables(List<String> positionLables) {
        this.positionLables = positionLables;
    }

    public List<String> getIndustryLables() {
        return industryLables;
    }

    public void setIndustryLables(List<String> industryLables) {
        this.industryLables = industryLables;
    }

    public List<String> getBusinessZones() {
        return businessZones;
    }

    public void setBusinessZones(List<String> businessZones) {
        this.businessZones = businessZones;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getFormatCreateTime() {
        return formatCreateTime;
    }

    public void setFormatCreateTime(String formatCreateTime) {
        this.formatCreateTime = formatCreateTime;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public Integer getAdWord() {
        return adWord;
    }

    public void setAdWord(Integer adWord) {
        this.adWord = adWord;
    }

    public Integer getResumeProcessRate() {
        return resumeProcessRate;
    }

    public void setResumeProcessRate(Integer resumeProcessRate) {
        this.resumeProcessRate = resumeProcessRate;
    }

    public Integer getResumeProcessDay() {
        return resumeProcessDay;
    }

    public void setResumeProcessDay(Integer resumeProcessDay) {
        this.resumeProcessDay = resumeProcessDay;
    }

    public String getImState() {
        return imState;
    }

    public void setImState(String imState) {
        this.imState = imState;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public Integer getPcShow() {
        return pcShow;
    }

    public void setPcShow(Integer pcShow) {
        this.pcShow = pcShow;
    }

    public Integer getAppShow() {
        return appShow;
    }

    public void setAppShow(Integer appShow) {
        this.appShow = appShow;
    }

    public Integer getDeliver() {
        return deliver;
    }

    public void setDeliver(Integer deliver) {
        this.deliver = deliver;
    }

    public String getGradeDescription() {
        return gradeDescription;
    }

    public void setGradeDescription(String gradeDescription) {
        this.gradeDescription = gradeDescription;
    }

    public String getPromotionScoreExplain() {
        return promotionScoreExplain;
    }

    public void setPromotionScoreExplain(String promotionScoreExplain) {
        this.promotionScoreExplain = promotionScoreExplain;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public Integer getIsSchoolJob() {
        return isSchoolJob;
    }

    public void setIsSchoolJob(Integer isSchoolJob) {
        this.isSchoolJob = isSchoolJob;
    }

    public String getSubwayline() {
        return subwayline;
    }

    public void setSubwayline(String subwayline) {
        this.subwayline = subwayline;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getLinestaion() {
        return linestaion;
    }

    public void setLinestaion(String linestaion) {
        this.linestaion = linestaion;
    }

    public String getHitags() {
        return hitags;
    }

    public void setHitags(String hitags) {
        this.hitags = hitags;
    }
}
