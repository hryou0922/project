package com.hry.lagou.lagou;

/**
 * 职位搜索条件
 */
public class JobSearchQry {
    private String first; // true
    private int pn; // 页码
    private String kd; // 关键字

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }
}
