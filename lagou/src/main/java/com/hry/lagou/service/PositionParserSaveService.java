package com.hry.lagou.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.hry.lagou.lagou.position.HRInfo;
import com.hry.lagou.lagou.position.PositionModel;
import com.hry.lagou.lagou.position.SinglePosition;
import com.hry.lagou.model.ConfigCollectionModel;
import com.hry.lagou.model.DownloadSaveInfoModel;
import com.hry.lagou.model.HRInfoModel;
import com.hry.lagou.model.SinglePositionModel;
import com.hry.lagou.utils.CommonBeanUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
            PositionModel positionModel = parserModelFromFile(file);
            if(positionModel == null){
                continue;
            }

            // hr info
            for(Map.Entry<String, HRInfo> hrInfoMap : positionModel.getContent().getHrInfoMap().entrySet()){
                HRInfo hrInfo = hrInfoMap.getValue();
                Integer hrUserId = hrInfo.getUserId(); // userId
                String positionId = hrInfoMap.getKey(); // 职位

                // HRInfoModel
                HRInfoModel hrInfoModel = new HRInfoModel();
                CommonBeanUtils.copyProperties(hrInfoModel, hrInfo);
                // 查询条件: hr query by Id
                Query hrUserIdQuery = new Query(Criteria.where("userId").is(hrUserId));
                HRInfoModel oldHrInfoModel = mongoTemplate.findOne(hrUserIdQuery, HRInfoModel.class, HRInfoModel.COLLECTION);

                if(oldHrInfoModel != null){
                    logger.info( "hr[{}]已经存在，增加新的推荐职位[{}]", hrUserId, positionId);
                    Set<String> positionIdSet = oldHrInfoModel.getPositionIdSet();
                    positionIdSet.add(positionId);
                    // 更新
                    Update update =  Update.update("positionIdSet", positionIdSet);
                    // 更新
                    mongoTemplate.updateFirst(hrUserIdQuery, update, ConfigCollectionModel.COLLECTION);
                }else {
                    logger.info("增加新的hr[{}]新的职位[{}]", hrUserId, positionId);
                    Set<String> positionIdSet = new HashSet<>();
                    positionIdSet.add(positionId);
                    hrInfoModel.setPositionIdSet(positionIdSet);
                    mongoTemplate.save(hrInfoModel, HRInfoModel.COLLECTION);
                }
            }

            // position info
            for(SinglePosition singlePosition : positionModel.getContent().getPositionResult().getResult()){
                SinglePositionModel oldSinglePositionModel = mongoTemplate.findOne(new Query(Criteria.where("positionId").is(singlePosition.getPositionId())), SinglePositionModel.class, SinglePositionModel.COLLECTION_PRE + kd);
                if(oldSinglePositionModel == null) {
                    // SinglePositionModel
                    SinglePositionModel singlePositionModel = new SinglePositionModel();
                    CommonBeanUtils.copyProperties(singlePositionModel, singlePosition);
                    singlePositionModel.parserSalary(); // 处理薪水值
                    logger.info("增加新职位记录[{}]", singlePositionModel.getPositionId());
                    mongoTemplate.save(singlePositionModel, SinglePositionModel.COLLECTION_PRE + kd);
                }else {
                    logger.info("[{}]职位已经存在 " , singlePosition.getPositionId());
                }
            }
        }
    }

    /**
     * 从本地文件中解析出PositionModel对象
     *  如果解析失败，则返回null
     * @param file
     * @return
     */
    private PositionModel parserModelFromFile(File file){
        if(!file.isFile()){
            logger.info("[{}] 不是文件，忽略", file.getAbsolutePath());
            return null;
        }
        if(file.length() == 0){
            logger.info("[{}] 文件长度为0，忽略", file.getAbsolutePath());
            return null;
        }
        logger.info("处理文件=[{}]", file.getAbsoluteFile());
        String json = null;
        PositionModel positionModel = null;
        try {
            json = FileUtils.readFileToString(file, "utf-8");
            positionModel = JSONObject.parseObject(json, PositionModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
            logger.warn("[{}] 不是json字符串，忽略，内容如下[{}]", file.getAbsoluteFile(), json);
        }
        return positionModel;
    }
}
