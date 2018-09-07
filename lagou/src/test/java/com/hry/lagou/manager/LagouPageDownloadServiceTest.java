package com.hry.lagou.manager;

import com.hry.lagou.Main;
import com.hry.lagou.dao.IConfigCollectionDao;
import com.hry.lagou.model.ConfigCollectionModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class LagouPageDownloadServiceTest {
    @Autowired
    private LagouPageDownloadManager lagouPageDownloadManager;

    @Test
    public void lagouPageDownloadManager(){
        lagouPageDownloadManager.execute();
    }



}
