package com.hry.lagou.manager;

import com.hry.lagou.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class PositionParserSaveManagerTest {
    @Autowired
    private PositionParserSaveManager positionParserSaveManager;

    @Test
    public void lagouPageDownloadManager(){
        positionParserSaveManager.execute();
    }



}
