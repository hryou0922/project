package com.hry.lagou.job;

import com.hry.lagou.manager.LagouPageDownloadManager;
import com.hry.lagou.manager.PositionParserSaveManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LagouTimerScheduler {
    private static final Logger log = LoggerFactory.getLogger(LagouTimerScheduler.class);
    @Autowired
    private LagouPageDownloadManager lagouPageDownloadManager;

    @Autowired
    private PositionParserSaveManager positionParserSaveManager;


    @Scheduled(initialDelay=1000, fixedRate=3600000)
    public void downloadPage() {
        lagouPageDownloadManager.execute();
        log.info("downloadPage--");
    }

    @Scheduled(initialDelay=1000, fixedRate=300000)
    public void parserLocalJson() {
        positionParserSaveManager.execute();
        log.info("parserLocalJson--");
    }

}
