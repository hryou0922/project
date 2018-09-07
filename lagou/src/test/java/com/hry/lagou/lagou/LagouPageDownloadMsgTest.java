package com.hry.lagou.lagou;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.http.LagouPageDownloadHttp;
import com.hry.lagou.model.SinglePositionModel;
import com.hry.lagou.lagou.position.PositionModel;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@Deprecated
public class LagouPageDownloadMsgTest {

    @Test
    public void parser(){
        SinglePositionModel singlePositionModel = new SinglePositionModel();
        singlePositionModel.setSalary("20k-25k");
        singlePositionModel.parserSalary();
        System.out.println(singlePositionModel.getSalary()
        + " " + singlePositionModel.getSalaryBegin() + " " +
        singlePositionModel.getSalaryEnd());
    }

    @Test
    public void downPage() throws IOException, InterruptedException {
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
        lagouPageDownloadMsg.initContext(loginParameter); // 初始化

        boolean first = true;
        // 下载数据
        int start = 1;
        for(int i = start; i <= 50; ) {
            if(i == start){
                jobSearchQry.setFirst("true");
            }else {
                jobSearchQry.setFirst("false");
            }
            jobSearchQry.setPn(i);
            String json = lagouPageDownloadMsg.request(loginParameter, jobSearchQry);
            System.out.println(json);
            FileUtils.write(new File("d:/tmp/lagou/" + jobSearchQry.getKd() +"/json_"+ i), json, "utf-8");

            // 随机
            Thread.sleep( 1000 * 30 + ThreadLocalRandom.current().nextLong(3000L));
            if(json.indexOf("您操作太频繁,请稍后再访问") > 0){
                lagouPageDownloadMsg = new LagouPageDownloadHttp();
                lagouPageDownloadMsg.initContext(loginParameter); // 初始化
                System.out.println("重新发送...");
            }else {
                i++;
            }
        }
    }

    @Test
    public void parserJson() throws IOException, InterruptedException {
        String json = FileUtils.readFileToString(new File("d:/tmp/json" + 2), "utf-8");
        PositionModel positionModel = JSONObject.parseObject(json, PositionModel.class);
        System.out.println(JSONObject.toJSONString(positionModel));
    }
}
