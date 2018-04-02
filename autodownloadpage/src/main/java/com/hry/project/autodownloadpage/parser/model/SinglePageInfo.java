package com.hry.project.autodownloadpage.parser.model;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hry.project.autodownloadpage.comic.model.*;

public class SinglePageInfo {
	private ComicAuthor comicAuthor; // 作者
	private List<ComicTag> comicTagList; // 标签
	private ComicQuality comicQuality;// 质量
	private ComicMainInfo comicMainInfo; // 主信息
	private ComicSub comicSub;// 子信息
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}

	public ComicAuthor getComicAuthor() {
		return comicAuthor;
	}

	public void setComicAuthor(ComicAuthor comicAuthor) {
		this.comicAuthor = comicAuthor;
	}

	public List<ComicTag> getComicTagList() {
		return comicTagList;
	}

	public void setComicTagList(List<ComicTag> comicTagList) {
		this.comicTagList = comicTagList;
	}

	public ComicQuality getComicQuality() {
		return comicQuality;
	}

	public void setComicQuality(ComicQuality comicQuality) {
		this.comicQuality = comicQuality;
	}

	public ComicMainInfo getComicMainInfo() {
		return comicMainInfo;
	}

	public void setComicMainInfo(ComicMainInfo comicMainInfo) {
		this.comicMainInfo = comicMainInfo;
	}

	public ComicSub getComicSub() {
		return comicSub;
	}

	public void setComicSub(ComicSub comicSub) {
		this.comicSub = comicSub;
	}
}
