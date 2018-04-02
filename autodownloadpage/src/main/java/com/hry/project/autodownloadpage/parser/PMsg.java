package com.hry.project.autodownloadpage.parser;

import java.util.Date;
import java.util.List;

import com.hry.project.autodownloadpage.comic.mapper.ComicLinksMapper;
import com.hry.project.autodownloadpage.comic.mapper.ComicMainInfoMapper;
import com.hry.project.autodownloadpage.comic.mapper.ComicQualityMapper;
import com.hry.project.autodownloadpage.comic.mapper.ComicSubMapper;
import com.hry.project.autodownloadpage.comic.model.*;
import com.hry.project.autodownloadpage.parser.model.SinglePageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PMsg {
	private static final Logger logger = LoggerFactory.getLogger(PMsg.class);
	@Autowired
	private SinglePageParser singlePageParser;
	
	@Autowired
	private ComicLinksMapper comicLinksMapper;
	
	@Autowired
	private ComicMainInfoMapper comicMainInfoMapper;
	
	@Autowired
	private ComicSubMapper comicSubMapper;
	
//	@Autowired
//	private ComicAuthor comicAuthor;
	
	@Autowired
	private ComicQualityMapper comicQualityMapper;
	
	public void execute(int num){
		int count = 0;
		List<ComicLinks> comicLinks = comicLinksMapper.selectAllWithUnDeal();
		for(ComicLinks link : comicLinks){
			logger.info("{},", count++);
			SinglePageInfo page = singlePageParser.parser(link.getUrl());
			if(page == null){
				logger.error("{} parser error!", link);
				continue;
			}
			// ComicMainInfo
			ComicMainInfo comicMainInfo = page.getComicMainInfo();
			comicMainInfo.setIndexUrl(link.getUrl());
			comicMainInfoMapper.insertSelective(comicMainInfo);
			// comicAuthor
			ComicAuthor comicAuthor = page.getComicAuthor();
			
			// ComicQuality
			ComicQuality comicQuality = page.getComicQuality();
			comicQuality.setComicId(comicMainInfo.getId());
			comicQualityMapper.insertSelective(comicQuality);
			// ComicSub
			ComicSub comicSub = page.getComicSub();
			comicSub.setComicsId(comicMainInfo.getId());
			comicSubMapper.insertSelective(comicSub);
			// 
			link.setUpdateTime(new Date());
			link.setDownloadStatus(1);
			comicLinksMapper.updateByPrimaryKeySelective(link);
		}
	}
}
