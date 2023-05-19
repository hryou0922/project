package com.hry.project.capcut.service;

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

    void saveContent2File(String contentFile);
}
