package com.hry.project.dictation.datamigration;

import com.hry.project.dictation.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 14:34
 */
public class DictationHis2WordMigrationMsgTest extends BaseTest {
    @Autowired
    private DictationHis2WordMigrationMsg dictationHis2WordMigrationMsg;

    @Test
    public void dictationHist2Word(){
        dictationHis2WordMigrationMsg.dictationHist2Word();
    }

    @Test
    public void updateWordGroupStaticInfo(){
        dictationHis2WordMigrationMsg.updateWordGroupStaticInfo();
    }
}
