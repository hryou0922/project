package com.hry.lagou.lagou;

public class LoginParameter {
    // city=杭州&cl=false&fromSearch=true&labelWords=&suginput=
    private String city;
    private String cl;
    private String fromSearch;
    private String labelWords;
    private String suginput;
    // city=%E6%9D%AD%E5%B7%9E&needAddtionalResult=false
    private String needAddtionalResult;

    public LoginParameter(){

    }

    public LoginParameter(String city,String cl,String fromSearch){
        this.city = city;
        this.cl = cl;
        this.fromSearch = fromSearch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getFromSearch() {
        return fromSearch;
    }

    public void setFromSearch(String fromSearch) {
        this.fromSearch = fromSearch;
    }

    public String getLabelWords() {
        return labelWords;
    }

    public void setLabelWords(String labelWords) {
        this.labelWords = labelWords;
    }

    public String getSuginput() {
        return suginput;
    }

    public void setSuginput(String suginput) {
        this.suginput = suginput;
    }

    public String getNeedAddtionalResult() {
        return needAddtionalResult;
    }

    public void setNeedAddtionalResult(String needAddtionalResult) {
        this.needAddtionalResult = needAddtionalResult;
    }
}
