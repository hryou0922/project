package com.hry.lagou.model;

import com.hry.lagou.Main;
import com.hry.lagou.dao.IConfigCollectionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class ConfigCollectionDaoTest {
    @Autowired
    private IConfigCollectionDao configCollectionDao;

    @Test
    public void updateConfig(){
        // 插入值
        String city = "杭州";
     //   String kds = "java;后端开发其它;c;net";
        String kds = "java;后端开发其它";
        int pageNum = 10;
        configCollectionDao.updateConfig(city, kds, pageNum);
    }

    @Test
    public void updateConfigTime() {
        // 插入值
        String city = "杭州";

        ConfigCollectionModel configCollectionModel = configCollectionDao.queryByCity(city);
        List<ConfigCollectionModel.ConfigModel> list = configCollectionModel.getConfigModelList();
        for(ConfigCollectionModel.ConfigModel configModel : list){
            configModel.setLastQuery(System.currentTimeMillis());
        }
        configCollectionDao.updateLastQueryTime(city, list);
    }


}
