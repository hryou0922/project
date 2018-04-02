package com.hry.project.autodownloadpage.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 下载代理信息
 * 
 * @author hry
 *
 */
@Component
public class AllSearchPageParser {
	private static final Logger logger = LoggerFactory.getLogger(AllSearchPageParser.class);

	public void parser() throws IOException, InterruptedException {
		int num = 400;
		for (int i = 44; i <= num; i++) {
			String url = "http://ac.qq.com/Comic/all/search/hot/page/" + i + "/";
			parser(url, i);
			Thread.sleep(1000 * 2);
		}
	}

	public void parser(String url, int i) throws IOException {
		File f = new File("D:/tmp/mahua/"+ i + ".txt");
		logger.info("request={}", url);
		Document doc = Jsoup.connect(url).get();
		// 从document中获取title值
		String title = doc.title();
		logger.info("LoadDocumentFromURL title={}", title);
		Elements trList = doc.select(".ret-search-item");
		int index = 1;
		ArrayList<String> col = new ArrayList<>();
		for (Element ele : trList) {
			Elements tdList = ele.select("h3 a");
			String absUrl = tdList.attr("abs:href");
			logger.info("{}, {}", index++, absUrl);
			col.add(absUrl);
		}
		FileUtils.writeLines(f, col);
	}
	
}
