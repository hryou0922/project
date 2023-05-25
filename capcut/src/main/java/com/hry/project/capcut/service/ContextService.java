package com.hry.project.capcut.service;

import com.google.gson.JsonObject;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:23
 */
public interface ContextService {

    /**
     * 获取文件内容
     * @return
     */
    String getContentFromFile();

    String getSonContentFromFile();

    /**
     * 获取文件内容
     * @return
     */
    JsonObject getJsonObjectContentFromFile();

    void saveContent2File(String contentFile);

    void saveJsonObjectContentFromFile(JsonObject jsonObject);
}
