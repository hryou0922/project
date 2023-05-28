package com.hry.project.capcut.service;

import com.hry.project.capcut.content.enums.LyricTruncatedModeEnum;
import com.hry.project.capcut.pojo.TemplateReturnInfoV1Vo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:25
 */
public interface TemplateV1Service {
    /**
     *
     * @param mp3FileName
     * @param picName
     * @param init 是否初始化
     * @param lyricTruncatedModeEnum 截断模式
     * @return
     */
    TemplateReturnInfoV1Vo execute(String mp3FileName, String picName, boolean init, LyricTruncatedModeEnum lyricTruncatedModeEnum);
}
