package com.hry.project.simple.csdnblog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangrongyou@yixin.im on 2018/4/2.
 */
public class CsdnBlogSinglePageParserUtil {

    public static List<ArticleBO> execute(String url){
        List<ArticleBO> articleBOList = new ArrayList<>();
        Document doc = null;
        try {
            System.out.println("request url = " + url);
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // 获取列表
        Element article_list = doc.getElementById("article_list");

        for(Element article : article_list.children()){
            ArticleBO articleBO = new ArticleBO();
            // 头部
            Element titleEle = article.select("span.link_title > a").first();
            if(titleEle == null){
                break;
            }
            // 文章连接
            articleBO.setHref(titleEle.attr("href"));
            // 文章标题
            articleBO.setTitle(titleEle.text().replaceAll("\\[.*\\]","").trim());
            // 发布日期
            Element postDateEle = article.select("span.link_postdate").first();
            articleBO.setPostDate(postDateEle.text());
            // 阅读次数
            Element linkViewEle = article.select("span.link_view").first();
            // 非数字全部替换掉
            articleBO.setNumberOfView(Integer.parseInt(linkViewEle.text().replaceAll("[^0-9]","")));

            articleBOList.add(articleBO);
        }
        return articleBOList;
    }
}
