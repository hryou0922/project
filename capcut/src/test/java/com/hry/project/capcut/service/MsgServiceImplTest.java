package com.hry.project.capcut.service;

import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.service.impl.MsgServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:56
 */
public class MsgServiceImplTest extends TestBase {
    @Autowired
    private MsgServiceImpl msgService;

    @Test
    public void exeute(){
        String mp3Name = "我只在乎你#邓丽君.mp3";
        String pic = "pexels-pixabay-46164.jpg";
        msgService.execute(mp3Name,pic);
    }

    @Test
    public void exeute2(){
        String mp3Name = "default.txt";
        msgService.execute(mp3Name);
    }
}
