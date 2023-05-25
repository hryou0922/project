package com.hry.project.capcut.service.impl;

import cn.hutool.core.io.FileUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.service.ContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:23
 */
@Slf4j
@Service
public class ContextServiceImpl implements ContextService {
    @Autowired
    private MyConfig myConfig;


    @Override
    public String getContentFromFile(){
        String file = myConfig.getParentContentDir() + File.separator + "draft_content.json";
        return FileUtil.readUtf8String(file);
    }

    @Override
    public void saveContent2File(String contentFile){
        String file = myConfig.getSonContentDir() + File.separator + "draft_content.json";
        FileUtil.writeUtf8String(contentFile, file);
    }

    @Override
    public String getSonContentFromFile(){
        String file = myConfig.getSonContentDir() + File.separator + "draft_content.json";
        return FileUtil.readUtf8String(file);
    }

    @Override
    public JsonObject getJsonObjectContentFromFile(){
        String file = myConfig.getSonContentDir() + File.separator + "draft_content.json";
        String content = FileUtil.readUtf8String(file);
        JsonElement jsonElement = JsonParser.parseString(content);
        return jsonElement.getAsJsonObject();
    }

    @Override
    public void saveJsonObjectContentFromFile(JsonObject jsonObject){
        String file = myConfig.getSonContentDir() + File.separator + "draft_content.json";
        FileUtil.writeUtf8String(jsonObject.toString(), file);
    }
}
