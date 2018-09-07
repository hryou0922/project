package com.hry.lagou.service;

import com.hry.lagou.http.LagouPageDownloadHttp;
import com.hry.lagou.model.ConfigCollectionModel;
import com.hry.lagou.lagou.JobSearchQry;
import com.hry.lagou.lagou.LoginParameter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 下载拉勾页面
 */
@Component
public class LagouPageDownloadService {
    private static final Logger logger = LoggerFactory.getLogger(LagouPageDownloadService.class);

    /**
     * 返回本次要查询的页面
     * @param configModel
     * @return
     */
    private int getQueryPage(ConfigCollectionModel.ConfigModel configModel){
        Long lastQuery = configModel.getLastQuery(); // 最后执行日期
        Integer pageNum = configModel.getPageNum(); // 每天假设增加的页数
        int perPage = pageNum == null ? 10 : pageNum;  // 每天增加的页面
        int totalPage = 100; // 首次查询是100页
        // 今天
        if(lastQuery != null){
            // 计算出本次要查询几页数据
            totalPage = ((int)((System.currentTimeMillis() - lastQuery)/1000/24/3600) + 1) * perPage;
        }
        return totalPage;
    }

    /**
     * 下载职位json并保存到本地
     * @param city 城市
     * @param configModel 配置
     * @param jsonSaveDir json的保存路径
     */
    public void execute(String city, ConfigCollectionModel.ConfigModel configModel, String jsonSaveDir){
        String kd = configModel.getKd(); // 查询参数

        // 初始化参数
        LoginParameter loginParameter = new LoginParameter();
        loginParameter.setCity(city);
        loginParameter.setCl("false");
        loginParameter.setFromSearch("true");
        loginParameter.setNeedAddtionalResult("false");

        // 请求参数
        JobSearchQry jobSearchQry = new JobSearchQry();
        jobSearchQry.setFirst("true");
        jobSearchQry.setPn(1);
        jobSearchQry.setKd(kd);

        LagouPageDownloadHttp lagouPageDownloadMsg = new LagouPageDownloadHttp();
        lagouPageDownloadMsg.initContext(loginParameter); // 初始化

        // 返回本次要查询的页面
        int totalPage = getQueryPage(configModel);
        boolean first = true;
        // 下载数据
        logger.info("下载记录 kd=[{}]，总页数 = [{}]", kd, totalPage);
        int start = 1;
        for(int i = start; i <= totalPage; ) {
            if(i == start){
                jobSearchQry.setFirst("true");
            }else {
                jobSearchQry.setFirst("false");
            }
            jobSearchQry.setPn(i);
            String json = lagouPageDownloadMsg.request(loginParameter, jobSearchQry);
            System.out.println(json);
            try {
                FileUtils.write(new File(jsonSaveDir + "/json_"+ i), json, "utf-8");
            } catch (IOException e) {
                logger.error("存储失败, e = {}", e);
                e.printStackTrace();
            }

            // 随机
            try {
                Thread.sleep( 1000 * 30 + ThreadLocalRandom.current().nextLong(3000L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(json.indexOf("您操作太频繁,请稍后再访问") > 0){
                lagouPageDownloadMsg = new LagouPageDownloadHttp();
                lagouPageDownloadMsg.initContext(loginParameter); // 初始化
                logger.info("重新发送...");
            }else {
                i++;
            }
        }
    }


}
