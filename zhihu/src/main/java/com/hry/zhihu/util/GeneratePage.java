package com.hry.zhihu.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GeneratePage {
    private String cookie;
    private String url;
    private RestTemplate restTemplate;
    private String titleName;
    public GeneratePage(String cookie, String titleName){
        restTemplate = new RestTemplate();
        this.cookie = cookie;
        // 添加文章url
        this.url = "https://mp.toutiao.com/core/article/edit_article_post/?source=mp&type=article";
        this.titleName = titleName;
    }

    public String generatePage(String content){


        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        requestHeaders.add("Cookie", cookie);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                popHeaders(content), requestHeaders);

        //发送请求，设置请求返回数据格式为String
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        String rtn = responseEntity.getBody();
        return rtn;
    }

    //组装请求体
    protected MultiValueMap<String, String> popHeaders(String content) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("title", titleName + "-123" );
    //    map.add("content","<p>s</p><p>3</p><p>4</p><p>5</p><p>6</p><p>=</p><p>=</p><p>=</p>");
        map.add("content",content);
        map.add("article_type","0");
        map.add("activity_tag","0");
        map.add("article_ad_type","3"); // article_ad_type 3
        map.add("save","0"); // 0表示表示成草稿
        //.....
        return map;
    }
}
