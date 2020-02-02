package com.hry.lajiao;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {

        Main main = new Main();

        String url = "https://cdn.ym188.vip/20191118/1iNC1SSg/";

        String[] rtnArray = main.executeIndex(url);
        System.out.println(Arrays.toString(rtnArray));

        String partIndex2 = rtnArray[2];


        String[] partArray2 = main.executeIndex2(url, partIndex2);
        List<String> partList = Arrays.stream(partArray2).filter(value -> !value.startsWith("#")).collect(Collectors.toList());
      //  System.out.println(Arrays.toString(partArray2));
      //  System.out.println(partList);
        System.out.println("start=" + partList.get(0) + " end = " + partList.get(partList.size()-1));
    }

    // 默认httpclient
    private CloseableHttpClient httpclient = HttpClients.createDefault();

    public String[] executeIndex(String url) throws IOException {
        url += "index.m3u8";
        RequestBuilder requestBuilder = RequestBuilder.get().setUri(url);
        CloseableHttpResponse response = httpclient.execute(requestBuilder.build());
        String content =  EntityUtils.toString(response.getEntity());
        return content.split("\\n");
    }

    public String[] executeIndex2(String url,String partIndex2) throws IOException {
        url += partIndex2;
        RequestBuilder requestBuilder = RequestBuilder.get().setUri(url);
        System.out.println("url =" + url);
        CloseableHttpResponse response = httpclient.execute(requestBuilder.build());
        String content =  EntityUtils.toString(response.getEntity());
        return content.split("\\n");
    }

}
