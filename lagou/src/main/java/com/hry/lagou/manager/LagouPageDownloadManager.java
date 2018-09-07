package com.hry.lagou.manager;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.dao.IConfigCollectionDao;
import com.hry.lagou.model.ConfigCollectionModel;
import com.hry.lagou.model.DownloadSaveInfoModel;
import com.hry.lagou.service.LagouPageDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LagouPageDownloadManager {
    private static  final Logger logger = LoggerFactory.getLogger(LagouPageDownloadManager.class);
    @Autowired
    private LagouPageDownloadService lagouPageDownloadService;
    @Autowired
    private IConfigCollectionDao configCollectionDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(){
        // 获取所有要查询的记录
        List<ConfigCollectionModel> configCollectionModels = mongoTemplate.findAll(ConfigCollectionModel.class,
                ConfigCollectionModel.COLLECTION);
        for(ConfigCollectionModel configCollectionModel : configCollectionModels){
            String city = configCollectionModel.getCity();
            List<ConfigCollectionModel.ConfigModel> configModelList = configCollectionModel.getConfigModelList();
            for(ConfigCollectionModel.ConfigModel configModel : configModelList){
                String kd = configModel.getKd();
                String jsonSaveDir = "D:\\tmp\\lagou\\" + kd + "\\" + System.currentTimeMillis() + "\\"; // 保存目录

                logger.info("begin deal kd = [{}], save dir = [{}]", kd, jsonSaveDir);
                lagouPageDownloadService.execute(city,configModel,jsonSaveDir);

                // 保存存在目录
                DownloadSaveInfoModel downloadSaveInfoModel = new DownloadSaveInfoModel();
                downloadSaveInfoModel.setDir(jsonSaveDir);
                downloadSaveInfoModel.setKd(kd);
                mongoTemplate.insert(downloadSaveInfoModel, DownloadSaveInfoModel.COLLECTION);
                logger.info("finish deal kd = [{}], save dir = [{}], savedir=[{}]",
                        kd, jsonSaveDir, JSONObject.toJSON(downloadSaveInfoModel));
                // 更新最后下载的时间
                configCollectionDao.updateLastQueryTime(city, configModelList);
            }

        }

    }
}
