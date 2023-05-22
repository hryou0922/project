package com.hry.project.capcut.service;

import com.google.gson.JsonObject;
import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.service.impl.ContextServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:36
 */
public class ContextServiceImplTest extends TestBase {
    @Autowired
    private ContextServiceImpl contextService;

    @Test
    public void read(){
        System.out.println(contextService.getContentFromFile());
    }

    @Test
    public void jsonObject(){
       JsonObject jsonObject = contextService.getJsonObjectContentFromFile();
       System.out.println(jsonObject.toString());
    }
}
