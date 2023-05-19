package com.hry.project.capcut.service;

import com.hry.project.capcut.pojo.FileMp3InfoVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:38
 */
public interface MsgService {
    FileMp3InfoVo execute(String mp3FileName, String picName);

    FileMp3InfoVo execute(String configFileName);
}
