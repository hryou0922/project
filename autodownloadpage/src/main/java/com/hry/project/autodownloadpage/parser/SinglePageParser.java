package com.hry.project.autodownloadpage.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hry.project.autodownloadpage.comic.model.*;
import com.hry.project.autodownloadpage.parser.model.SinglePageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * 单个文件
 * 
 * @author hry
 *
 */
@Component
public class SinglePageParser {
	private static final Logger logger = LoggerFactory.getLogger(SinglePageParser.class);

	public SinglePageInfo parser(String url){
		logger.info("request={}", url);
		SinglePageInfo page = new SinglePageInfo();
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			// 作品信息
			Element worksIntro = doc.select(".works-intro").first();
			if(worksIntro == null || doc.select(".works-author-name").first() == null){
				return null;
			}
		//	logger.info("{}",worksIntro);
			
			
			// 作者
			ComicAuthor comicAuthor = new ComicAuthor();
			page.setComicAuthor(comicAuthor);
			comicAuthor.setName(doc.select(".works-author-name").first().text());
			comicAuthor.setNumOfWork(Integer.parseInt(doc.select(".works-author-count em").first().text()));
		//	comicAuthor.setType(type);
			comicAuthor.setAuthNotice(doc.select(".works-author-notice").first().text());
			
			// 标签 : TODO 返回的字符串不是html而是一段代码，暂时无法获取
			List<ComicTag> comicTagList = new ArrayList<>();
		//	logger.info("{},{}",worksIntro.select("tags-show a"), worksIntro.select(".tags-show").first());
			for(Element e : worksIntro.select("tags-show a")){
				ComicTag comicTag = new ComicTag();
				String tagname = e.text();
				comicTag.setTagname(tagname);
				comicTagList.add(comicTag);
			}
			page.setComicTagList(comicTagList);
			
			// 质量
			ComicQuality comicQuality = new ComicQuality();
			for(Element e : worksIntro.select(".works-intro-head i")){
				comicQuality.setQuality(e.text());
			}
			page.setComicQuality(comicQuality);
			
			// 主信息
			ComicMainInfo comicMainInfo = new ComicMainInfo();
			page.setComicMainInfo(comicMainInfo);
			// 子信息
			ComicSub comicSub = new ComicSub();
			page.setComicSub(comicSub);
			Elements worksIntroSpan = worksIntro.select(".works-intro-digi span");
			// 作者名称
			comicMainInfo.setName(worksIntro.select(".works-intro-title").first().text());
			// 评价人数量
			comicSub.setNumOfEvaluator(Integer.parseInt(worksIntro.select(".works-score p span").first().text()));
			// 评分
			comicSub.setScore(worksIntro.select(".works-score p strong").first().text());
			// 图文作者
			comicMainInfo.setCharAuthor(worksIntroSpan.get(0).select("em").text());
			// 人气
			comicSub.setPopularity(worksIntroSpan.get(1).select("em").text());
			// 收藏数目
			comicSub.setNumOfCollections(Integer.parseInt(worksIntroSpan.get(2).select("em").text()));
			// 简介
			comicMainInfo.setIntroduction(worksIntro.select(".works-intro-short").first().text());
			// 红票
			Elements voteLi = doc.select(".works-vote-list li");
			comicSub.setNumOfRedVote(Integer.parseInt(voteLi.get(0).select("strong").first().text()));
			// 黑票
			comicSub.setNumOfBlackVote(Integer.parseInt(voteLi.get(1).select("strong").first().text()));
			
			// 这一部分内容有些可能没有: 月票的值是--
			// 今日月票： dateMT_total
			Elements dateMTTotalEle = doc.select("#dateMT_total");
			if(dateMTTotalEle != null && dateMTTotalEle.size() > 0 ){
			//	comicSub.setMonthTicketToday(Integer.parseInt(dateMTTotalEle.first().text()));
			}
			// 本月月票 monthMT_total
			Elements monthMTTotalEle = doc.select("#monthMT_total");
			if(monthMTTotalEle != null && monthMTTotalEle.size() > 0){
			//	comicSub.setMonthTicket(Integer.parseInt(monthMTTotalEle.first().text()));
			}
			// 本月排名 rankMT_no 
			Elements rankMTNoEle = doc.select("#rankMT_no");
			if(dateMTTotalEle != null && rankMTNoEle.size() > 0){
			//	comicSub.setMonthTickerRank(Integer.parseInt(rankMTNoEle.first().text()));
			}
			
			// 最新  works-chapter-item
			Element worksChapterItem = doc.select(".works-chapter-item").last();
			comicSub.setLastUpdateChapter(worksChapterItem.text());
			logger.info("{}", worksChapterItem.text());
			// 
			logger.info("lagou info ={}", page);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
}
