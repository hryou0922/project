package com.hry.lagou.manager;

import com.hry.lagou.model.DownloadSaveInfoModel;
import com.hry.lagou.service.PositionParserSaveService;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PositionParserSaveManager {
    private  static final Logger logger = LoggerFactory.getLogger(PositionParserSaveManager.class);
    @Autowired
    private PositionParserSaveService positionParserSaveService;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(){
        List<DownloadSaveInfoModel> downloadSaveInfoModelList = mongoTemplate.findAll(DownloadSaveInfoModel.class, DownloadSaveInfoModel.COLLECTION);
        for(DownloadSaveInfoModel downloadSaveInfoModel : downloadSaveInfoModelList){

            String jsonSaveDir =  downloadSaveInfoModel.getDir();
            String kd = downloadSaveInfoModel.getKd();
            logger.info("处理请求 kd  = [{}], jsonDir = [{}]", kd,jsonSaveDir);
            positionParserSaveService.execute(downloadSaveInfoModel);

            DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where("dir").is(jsonSaveDir)), DownloadSaveInfoModel.COLLECTION);
            logger.info("=========删除处理的请求 kd  = [{}], jsonDir = [{}]，结果=[{}]", kd,jsonSaveDir, deleteResult);
        }
    }
}
