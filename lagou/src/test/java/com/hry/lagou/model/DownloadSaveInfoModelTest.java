package com.hry.lagou.model;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.Main;
import com.hry.lagou.dao.IConfigCollectionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class DownloadSaveInfoModelTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void generateDownloadSaveInfoModel(){
        String rootDir = "D:\\tmp\\lagou";
        String kds = "java;后端开发其它";
        for(String kd : kds.split(";")) {
            File kdRootList = new File(rootDir + "\\" + kd);
            for(File f : kdRootList.listFiles()) {
                DownloadSaveInfoModel downloadSaveInfoModel = new DownloadSaveInfoModel();
                downloadSaveInfoModel.setKd(kd);
                downloadSaveInfoModel.setDir(f.getAbsolutePath());
                System.out.println(JSONObject.toJSONString(downloadSaveInfoModel));
                mongoTemplate.save(downloadSaveInfoModel, DownloadSaveInfoModel.COLLECTION);
            }
        }
    }
}
