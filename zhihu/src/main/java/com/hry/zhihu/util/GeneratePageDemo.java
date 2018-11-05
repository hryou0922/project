package com.hry.zhihu.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GeneratePageDemo {
    public static void main(String[] args){
        generatePage();
    }

    public static void generatePage(){
        RestTemplate restTemplate = new RestTemplate();
        // 添加文章url
        String url = "https://mp.toutiao.com/core/article/edit_article_post/?source=mp&type=article";
        // header
        String cookie = "ccid=263a11f4018ecd2eca267e10e0c031b1; UM_distinctid=16668278eb31dd-0d5fb130af83f6-8383268-e1000-16668278eb55d6; sso_uid_tt=d3c1f83fa623bea8762c126274404d9e; toutiao_sso_user=75d455d6fbde8c028ec81169edfd15a9; sso_login_status=1; sessionid=607c11d8a0c7b383a0f35977ad3c457c; _mp_test_key_1=93cee2248abd631a63bad77db4dfd0fa; uid_tt=1853dea8796a40852f5e2ed5c39350ef; uuid=\"w:c41582fb05b648f28a5a43b9ea85b2e9\"; tt_im_token=1539345792910678793567441543526038082753779389693883707710580625; _ga=GA1.2.908201833.1539347059; _ba=BA0.2-20181013-5110e-4aj2ButvOQ0WfekENwDm; _mp_auth_key=a3d7be0dd54f6aa94c7282a01ccfb382; _ga=GA1.3.908201833.1539347059; __tea_sdk__ssid=8080974e-b431-491e-bb57-1503b37e7fe6; tt_webid=6612070268485600782; __tea_sdk__user_unique_id=105182348856; ptcn_no=98c1222dd41fc8c152496cef10eefae0";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        requestHeaders.add("Cookie", cookie);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                popHeaders(null), requestHeaders);

        //发送请求，设置请求返回数据格式为String
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    //组装请求体
    protected static MultiValueMap<String, String> popHeaders(Object AnswerData) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("title", "待处理文章草稿-不可以直接发布");
        map.add("content","<p>s</p><p>3</p><p>4</p><p>5</p><p>6</p><p>=</p><p>=</p><p>=</p>");
        map.add("article_type","0");
        map.add("activity_tag","0");
        map.add("article_ad_type","2");
        map.add("save","0"); // 0表示表示成草稿
        //.....
        return map;
    }
}
