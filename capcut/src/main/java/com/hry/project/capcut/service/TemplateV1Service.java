package com.hry.project.capcut.service;

import com.hry.project.capcut.pojo.TemplateReturnInfoV1Vo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:25
 */
public interface TemplateV1Service {
    TemplateReturnInfoV1Vo execute(String mp3FileName, String picName);
}
