package com.hry.lagou.dao.impl;

import com.hry.lagou.model.ConfigCollectionModel;
import com.hry.lagou.dao.IConfigCollectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置参数Dao
 */
@Component
public class ConfigCollectionDaoImpl implements IConfigCollectionDao {
    private static final Logger logger = LoggerFactory.getLogger(ConfigCollectionDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存
     * @param configCollectionModel
     */
    @Override
    public void insert(ConfigCollectionModel configCollectionModel){
        mongoTemplate.save(configCollectionModel, ConfigCollectionModel.COLLECTION);
    }

    @Override
    public ConfigCollectionModel queryByCity(String city){
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        ConfigCollectionModel configCollectionModel = mongoTemplate.findOne(query, ConfigCollectionModel.class, ConfigCollectionModel.COLLECTION);
        return configCollectionModel;
    }

    @Override
    public void updateConfig(String city, String kds, int pageNum){
//        String city = "杭州";
//        String kds = "java;后端开发其它";
//        int pageNum = 10;
        boolean isFirst = false; // 第一次创建
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        ConfigCollectionModel configCollectionModel = mongoTemplate.findOne(query, ConfigCollectionModel.class, ConfigCollectionModel.COLLECTION);
        // 主对象
        if(configCollectionModel == null){
            isFirst = true;
            configCollectionModel = new ConfigCollectionModel();
            configCollectionModel.setCity(city);
        }
        // List
        List<ConfigCollectionModel.ConfigModel> list = configCollectionModel.getConfigModelList();
        if(list == null){
            list = new ArrayList<>();
            configCollectionModel.setConfigModelList(list);
        }
        for(String kd : kds.split(";")){
            boolean isFind = false;
            for(ConfigCollectionModel.ConfigModel configModel : list){
                if(kd.equals(configModel.getKd())){
                    logger.warn("{}已经存在", configModel.getKd());
                    isFind = true;
                    break;
                }
            }
            if(!isFind) {
                logger.warn("{}新增", kd);
                ConfigCollectionModel.ConfigModel configModel = new ConfigCollectionModel.ConfigModel();
                configModel.setKd(kd);
                configModel.setPageNum(pageNum);
                list.add(configModel);
            }
        }
        if(isFirst){
            // 添加记录
            logger.info("添加记录");
            mongoTemplate.insert(configCollectionModel, ConfigCollectionModel.COLLECTION);
        }else {
            // 更新记录
            logger.info("更新记录");
            // 更新
            Update update =  Update.update("configModelList", list);
            // 更新
            mongoTemplate.updateFirst(query, update, ConfigCollectionModel.COLLECTION);
        }
    }

    /**
     * 更新最后更新的时间
     */
    @Override
    public void updateLastQueryTime(String city, List<ConfigCollectionModel.ConfigModel> list){
        // 查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is("杭州"));
        for(ConfigCollectionModel.ConfigModel configModel : list){
            configModel.setLastQuery(System.currentTimeMillis());
        }
        // 更新
        Update update =  Update.update("configModelList", list);
        // 更新
        mongoTemplate.updateFirst(query, update, ConfigCollectionModel.COLLECTION);
    }

}
