package com.hry.project.autodownloadpage.comic.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ComicSub {
    private Integer comicsId;

    private Long popularity;

    private Integer lastUpdateChapter;

    private Integer numOfEvaluator;

    private Integer numOfCollections;

    private Integer numOfRedVote;

    private Integer numOfBlackVote;

    private Integer score;

    private Integer monthTicketToday;

    private Integer monthTicket;

    private Date createTime;

    private Integer monthTickerRank;

    private Date updateTime;
    
    public void setPopularity(String s){
    	if(!StringUtils.isEmpty(s)){
    		s.replace(",", "");
    		if(s.endsWith("亿")){
    			// 132.6亿
    			popularity = (long)(Double.parseDouble(s.replace("亿", "")) * 100000000);
    		}else if(s.endsWith("万")){
    			// 2.1万
    			popularity = (long)(Double.parseDouble(s.replace("万", "")) * 10000);
    		}else{
    			// 9,395
    			popularity = (long)(Double.parseDouble(s));
    		}
    	}
    }
    
    public void setScore(String score) {
    	if(!StringUtils.isEmpty(score)){
    		this.score = (int)(Double.parseDouble(score) * 10);
    	}
    }
    
//    public void set(String MonthTicketToday ){
//    	
//    }
    
    public void setLastUpdateChapter(String lastUpdateChapter) {
    	if(!StringUtils.isEmpty(lastUpdateChapter)){
    		Pattern patter = Pattern.compile("\\d+");
    		Matcher mathcher = patter.matcher(lastUpdateChapter);
    		while(mathcher.find()){
    			this.lastUpdateChapter = Integer.parseInt(mathcher.group());
    		}
    	 
    	//	this.lastUpdateChapter = Integer.parseInt(patter.matcher(lastUpdateChapter).group(0));
    	}
    }
    public static void main(String[] args) {
		ComicSub sub = new ComicSub();
	//	sub.setLastUpdateChapter("第162话 难以抗拒的心……");
		sub.setLastUpdateChapter("a162bab");
		System.out.println(sub.getLastUpdateChapter());
	}
    
    public Integer getComicsId() {
        return comicsId;
    }

    public void setComicsId(Integer comicsId) {
        this.comicsId = comicsId;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    public Integer getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(Integer lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter;
    }

    public Integer getNumOfEvaluator() {
        return numOfEvaluator;
    }

    public void setNumOfEvaluator(Integer numOfEvaluator) {
        this.numOfEvaluator = numOfEvaluator;
    }

    public Integer getNumOfCollections() {
        return numOfCollections;
    }

    public void setNumOfCollections(Integer numOfCollections) {
        this.numOfCollections = numOfCollections;
    }

    public Integer getNumOfRedVote() {
        return numOfRedVote;
    }

    public void setNumOfRedVote(Integer numOfRedVote) {
        this.numOfRedVote = numOfRedVote;
    }

    public Integer getNumOfBlackVote() {
        return numOfBlackVote;
    }

    public void setNumOfBlackVote(Integer numOfBlackVote) {
        this.numOfBlackVote = numOfBlackVote;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMonthTicketToday() {
        return monthTicketToday;
    }

    public void setMonthTicketToday(Integer monthTicketToday) {
        this.monthTicketToday = monthTicketToday;
    }

    public Integer getMonthTicket() {
        return monthTicket;
    }

    public void setMonthTicket(Integer monthTicket) {
        this.monthTicket = monthTicket;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMonthTickerRank() {
        return monthTickerRank;
    }

    public void setMonthTickerRank(Integer monthTickerRank) {
        this.monthTickerRank = monthTickerRank;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}