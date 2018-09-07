package com.hry.lagou.http;

import com.hry.lagou.lagou.JobSearchQry;
import com.hry.lagou.lagou.LoginParameter;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 下载lagou页面
 */
public class LagouPageDownloadHttp {
    private static final Logger logger = LoggerFactory.getLogger(LagouPageDownloadHttp.class);
    // lagou
    private String lagouHostUrl = "https://www.lagou.com";
    // 请求Referer Url中的值
    private String refererUrl = "";

    // 默认httpclient
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    // 创建CookieStore实例
    static CookieStore cookieStore = null;
    // 创建上下文
    static HttpClientContext context = null;

    /**
     * 第一次登录，初始化上下文，
     *  主要下次请求带上user_trace_token和SEARCH_ID
     */
    public void initContext( LoginParameter loginParameter) {
        try {
            String url = lagouHostUrl + "/jobs/list_java";
            RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
            requestBuilder.addParameter("city", loginParameter.getCity());
            requestBuilder.addParameter("cl", loginParameter.getCl());
            requestBuilder.addParameter("fromSearch", loginParameter.getFromSearch());
            requestBuilder.addParameter("labelWords", "");
            requestBuilder.addParameter("suginput", "");

            this.refererUrl = requestBuilder.getUri().toURL().toString();
            logger.info("referer rul = {}", this.refererUrl);
            // 设置header
            requestBuilder.setHeader("Content-Type", "application/x-www-form-urlencoded");
            requestBuilder.setHeader("Connection", "keep-alive");
            requestBuilder.setHeader("Upgrade-Insecure-Requests", "1");
            requestBuilder.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            requestBuilder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            requestBuilder.setHeader("Accept-Encoding", "gzip, deflate, br");
            requestBuilder.setHeader("Accept-Language", "zh-CN,zh;q=0.9,zh-TW;q=0.8");
            //    requestBuilder.setHeader("Referer","https://www.lagou.com");
            requestBuilder.setHeader("Referer", lagouHostUrl);

            CloseableHttpResponse response = httpclient.execute(requestBuilder.build());

            for (Header header : response.getAllHeaders()) {
                logger.info(header.getName() + " ======= " + header.getValue());
            }

            // cookie store
            //       setCookieStore(response);
            // context
            setContext();

            // 关闭请求
            response.close();
        }catch (IOException e){
            logger.error("第一次登录，初始化上下文， 下载拉勾页面失败, msg = {}", e);
        }
    }

    /**
     * 普通请求职位
     * @param loginParameter
     * @param jobSearchQry
     * @return
     * @throws IOException
     */
    public String request(LoginParameter loginParameter, JobSearchQry jobSearchQry){
        String url = lagouHostUrl + "/jobs/positionAjax.json";
        RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
        // url
        requestBuilder.addParameter("city", loginParameter.getCity());
        requestBuilder.addParameter("needAddtionalResult",loginParameter.getNeedAddtionalResult());
        // header
        requestBuilder.setHeader("Content-Type","application/x-www-form-urlencoded");
        requestBuilder.setHeader("Origin","https://www.lagou.com");
        requestBuilder.setHeader("X-Anit-Forge-Code","0");
        requestBuilder.setHeader("Accept-Encoding","gzip, deflate, br");
        requestBuilder.setHeader("Accept-Language","zh-CN,zh;q=0.9,zh-TW;q=0.8");
        requestBuilder.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        requestBuilder.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        requestBuilder.setHeader("X-Requested-With","XMLHttpRequest");
        requestBuilder.setHeader("Connection","keep-alive");
        requestBuilder.setHeader("X-Anit-Forge-Token","None");
//        requestBuilder.setHeader("Referer","https://www.lagou.com/jobs/list_java?city=%E6%9D%AD%E5%B7%9E&cl=false&fromSearch=true&labelWords=&suginput=");
        requestBuilder.setHeader("Referer",this.refererUrl); // 必须有这个值

        // 	模拟form表单：UrlEncodedFormEntity
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("first", jobSearchQry.getFirst()));
        formparams.add(new BasicNameValuePair("pn", String.valueOf(jobSearchQry.getPn())));
        formparams.add(new BasicNameValuePair("kd", jobSearchQry.getKd()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        requestBuilder.setEntity(entity);
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = httpclient.execute(requestBuilder.build(), context);
            return EntityUtils.toString(closeableHttpResponse.getEntity());
        } catch (IOException e) {
            logger.error("下载拉勾页面失败, msg = {}", e);
        }
        return  null;
    }

    /**
     * 设置上下文
     */
    private void setContext() {
        context = HttpClientContext.create();
        Registry<CookieSpecProvider> registry = RegistryBuilder
                .<CookieSpecProvider> create()
//                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
//                .register(CookieSpecs.BROWSER_COMPATIBILITY,
//                        new BrowserCompatSpecFactory())
                .build();
        context.setCookieSpecRegistry(registry);
    }

    /**
     * 设置cookie值
     *  暂时不用
     * @param httpResponse
     */
    private void setCookieStore(HttpResponse httpResponse) {
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
        logger.info("JSESSIONID:" + JSESSIONID);
        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
        cookie.setVersion(0);
//        cookie.setDomain("127.0.0.1");
//        cookie.setPath("/CwlProClient");
        // cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
        // cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
        // cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
        // cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
        cookieStore.addCookie(cookie);
        // 保存到上下文
        context.setCookieStore(cookieStore);
    }





}
