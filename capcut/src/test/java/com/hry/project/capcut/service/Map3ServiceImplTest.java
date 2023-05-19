package com.hry.project.capcut.service;

import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.service.impl.Mp3ServiceImpl;
import com.hry.project.capcut.utils.GsonBox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/12 23:38
 */
public class Map3ServiceImplTest extends TestBase {
    @Autowired
    private Mp3ServiceImpl map3Service;

    @Test
    public void parser(){
        // 158066666
        String name = "小城故事#邓丽君.mp3";
        FileMp3InfoVo fileMp3InfoVo = map3Service.parser(name);
        System.out.println(GsonBox.PUBLIC.toJson(fileMp3InfoVo));
    }
}
