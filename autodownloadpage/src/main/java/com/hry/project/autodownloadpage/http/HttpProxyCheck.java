package com.hry.project.autodownloadpage.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpProxyCheck {
	private static final Logger log = LoggerFactory.getLogger(HttpProxyCheck.class);
	
	public boolean checkProxyWork(String ip, int port) throws ClientProtocolException, IOException{
		boolean proxyWork = false;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	HttpHost proxy = new HttpHost(ip, port);
        	RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(5 * 1000)
                    .setRedirectsEnabled(true)
                    .setMaxRedirects(1000)
                    .setSocketTimeout(5 * 1000)
                    .build();
         //   HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        //	HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        	HttpGet httpGet = new HttpGet("http://www.google.com/");
            httpGet.setConfig(config);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
                log.info("{}",response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
            //    EntityUtils.consume(entity1);
                log.info("respons={}", EntityUtils.toString(entity1));
            } finally {
                response1.close();
            }
        } finally {
            httpclient.close();
        }
		
		return proxyWork;
	}
}
