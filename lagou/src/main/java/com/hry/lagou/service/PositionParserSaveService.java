package com.hry.lagou.service;

import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.lagou.position.HRInfo;
import com.hry.lagou.lagou.position.PositionModel;
import com.hry.lagou.lagou.position.SinglePosition;
import com.hry.lagou.model.DownloadSaveInfoModel;
import com.hry.lagou.model.HRInfoModel;
import com.hry.lagou.model.SinglePositionModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class PositionParserSaveService {
    private static final Logger logger = LoggerFactory.getLogger(PositionParserSaveService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(DownloadSaveInfoModel downloadSaveInfoModel){
        String dir = downloadSaveInfoModel.getDir(); // 保存目录
        String kd = downloadSaveInfoModel.getKd(); // 搜索关键字
        for(File file : new File(dir).listFiles()){
            if(!file.isFile()){
                logger.info("[{}] 不是文件，忽略", file.getAbsolutePath());
            }
            logger.info("处理文件=[{}]", file.getAbsoluteFile());
            String json = null;
            try {
                json = FileUtils.readFileToString(file, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            PositionModel positionModel = JSONObject.parseObject(json, PositionModel.class);

            // hr info
            for(Map.Entry<String, HRInfo> hrInfoMap : positionModel.getContent().getHrInfoMap().entrySet()){
                HRInfo hrInfo = hrInfoMap.getValue();

                // HRInfoModel
                HRInfoModel hrInfoModel = new HRInfoModel();
                try {
                    BeanUtils.copyProperties(hrInfoModel, hrInfo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                List<HRInfoModel> hrInfoOldList = mongoTemplate.find(new Query(Criteria.where("userId").is(hrInfo.getUserId())), HRInfoModel.class, HRInfoModel.COLLECTION);
           //     logger.info("===" + hrInfoOldList);
                if(hrInfoOldList != null && hrInfoOldList.size() > 0){
                    logger.info(hrInfo.getUserId() + "已经存在，增加职位" + hrInfoMap.getKey());
                    HRInfoModel hrInfoSize1 = hrInfoOldList.get(0);
                    Set<String> positionIdSet = hrInfoSize1.getPositionIdSet();
                    if(positionIdSet == null){
                        positionIdSet = new HashSet<>();
                        hrInfoSize1.setPositionIdSet(positionIdSet);
                    }
                    positionIdSet.add(hrInfoMap.getKey());
                    mongoTemplate.save(hrInfoSize1, HRInfoModel.COLLECTION);
                }else {
                    logger.info(hrInfo.getUserId() + "增加职位" + hrInfoMap.getKey());
                    Set<String> positionIdSet = new HashSet<>();
                    positionIdSet.add(hrInfoMap.getKey());
                    hrInfo.setPositionIdSet(positionIdSet);
                    mongoTemplate.save(hrInfoModel, HRInfoModel.COLLECTION);
                }
            }

            // position info
            for(SinglePosition singlePosition : positionModel.getContent().getPositionResult().getResult()){
                List<SinglePositionModel> hrInfoOldList = mongoTemplate.find(new Query(Criteria.where("positionId").is(singlePosition.getPositionId())), SinglePositionModel.class, SinglePositionModel.COLLECTION_PRE + kd);
                if(hrInfoOldList.size() == 0) {
                    // SinglePositionModel
                    SinglePositionModel singlePositionModel = new SinglePositionModel();
                    try {
                        BeanUtils.copyProperties(singlePositionModel, singlePosition);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    singlePositionModel.parserSalary(); // 处理薪水值
                    logger.info("增加职位记录 " + singlePositionModel.getPositionId());
                    mongoTemplate.save(singlePositionModel, SinglePositionModel.COLLECTION_PRE + kd);
                }else {
                    logger.info("已经职位存在记录 " + singlePosition.getPositionId());
                }
            }
        }


    }
}
