package com.hry.project.autodownloadpage.comic.model;

import org.springframework.util.Assert;

public class ComicQuality {
	public static final int QUALITY_OPEN = 1; // 开启
    private Integer comicId;

    private Integer right;

    private Integer pink;

    private Integer pop;

    private Integer rookie;
    
    private Integer exclusive;

    /**
     * 设置成功返回true
     * 
     * @param quality
     */
    public boolean setQuality(String quality){
    	Assert.notNull(quality, "quality can't be null!");
    	boolean success = true;
    	switch (quality) {
		case "签约":
			this.right = QUALITY_OPEN;
			break;
		case "精品":
			this.pink = QUALITY_OPEN;
			break;
		case "热门":
			this.pop = QUALITY_OPEN;
			break;
		case "新手":
			this.rookie = QUALITY_OPEN;
			break;
		case "独家":
			this.exclusive = QUALITY_OPEN;
			break;
		default:
			success = false;
			break;
		}
    	return success;
    }
    
    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getPink() {
        return pink;
    }

    public void setPink(Integer pink) {
        this.pink = pink;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

    public Integer getRookie() {
        return rookie;
    }

    public void setRookie(Integer rookie) {
        this.rookie = rookie;
    }

	public Integer getExclusive() {
		return exclusive;
	}

	public void setExclusive(Integer exclusive) {
		this.exclusive = exclusive;
	}
    
}