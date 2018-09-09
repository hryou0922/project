package com.hry.lagou.lagou.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.lagou.JobSearchQry;
import com.hry.lagou.http.LagouPageDownloadHttp;
import com.hry.lagou.lagou.LoginParameter;
import com.hry.lagou.lagou.position.PositionModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Deprecated
public class MongoTemplateMsg {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save()  {
      //  PositionModel positionModel = new PositionModel();
     //   mongoTemplate.save(positionModel);
        try {
            a();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void a() throws IOException, InterruptedException {
        // 初始化参数
        LoginParameter loginParameter = new LoginParameter();
        loginParameter.setCity("杭州");
        loginParameter.setCl("false");
        loginParameter.setFromSearch("true");
        loginParameter.setNeedAddtionalResult("false");

        // 请求参数
        JobSearchQry jobSearchQry = new JobSearchQry();
        jobSearchQry.setFirst("true");
        jobSearchQry.setPn(1);
        jobSearchQry.setKd("java");

        LagouPageDownloadHttp lagouPageDownloadMsg = new LagouPageDownloadHttp();
        // 初始化
        lagouPageDownloadMsg.initContext(loginParameter);
        // 下载数据
        for(int i = 1; i <= 1; i++) {
            jobSearchQry.setPn(i);
            String json = lagouPageDownloadMsg.request(loginParameter, jobSearchQry);
            System.out.println(json);
            FileUtils.write(new File("d:/tmp/json" + i), json, "utf-8");
            Thread.sleep( 1000 * 3);
            PositionModel positionModel = JSONObject.parseObject(json, PositionModel.class);
            mongoTemplate.save(positionModel);
        }
    }
}
