package com.hry.project.capcut.service;

import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.pojo.TemplateReturnInfoV1Vo;
import com.hry.project.capcut.service.impl.TemplateV1ServiceImpl;
import com.hry.project.capcut.utils.GsonBox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:42
 */
public class TemplateV1ServiceImplTest extends TestBase {
    @Autowired
    private TemplateV1ServiceImpl templateV1Service;

    @Test
    public void executeInitTrue(){
        String mp3FileName = "小城故事#邓丽君.mp3";
        String picName = "黄昏.jpg";
        TemplateReturnInfoV1Vo templateReturnInfoV1Vo = templateV1Service.execute(mp3FileName, picName, true);
        System.out.println(GsonBox.PUBLIC.toJson(templateReturnInfoV1Vo));
    }


    @Test
    public void executeInitFalse(){
        String mp3FileName = "小城故事#邓丽君.mp3";
        String picName = "黄昏.jpg";
        TemplateReturnInfoV1Vo templateReturnInfoV1Vo = templateV1Service.execute(mp3FileName, picName, false);
        System.out.println(GsonBox.PUBLIC.toJson(templateReturnInfoV1Vo));
    }
}
