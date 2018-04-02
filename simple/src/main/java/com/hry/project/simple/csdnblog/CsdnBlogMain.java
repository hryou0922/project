package com.hry.project.simple.csdnblog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangrongyou@yixin.im on 2018/4/2.
 */
public class CsdnBlogMain {

    public static void main(String[] args) {
        String urlPrex = "https://blog.csdn.net/sunhf_csdn/article/list/";
        // String urlPrex = "https://blog.csdn.net/hry2015/article/list/";
        int page = 10; // 参考的博客页面数

        CsdnBlogManger csdnBlogManger = new CsdnBlogManger(urlPrex, page);
        csdnBlogManger.parser();
        csdnBlogManger.printAll();
        System.out.println("阅读量总数 = " + csdnBlogManger.countNumberOfView());

        // 按照阅读数进行排列
        csdnBlogManger.getUnModifiableListArticleBOListGlobal().stream().sorted(Comparator.comparingInt(ArticleBO::getNumberOfView))
                .forEach(o -> System.out.println(o));
    }
}
