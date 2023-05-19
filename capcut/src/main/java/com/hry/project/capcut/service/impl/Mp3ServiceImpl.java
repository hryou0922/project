package com.hry.project.capcut.service.impl;

import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.service.Mp3Service;
import com.hry.project.capcut.utils.VideoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


/**
 * 解析音频： https://blog.csdn.net/li1325169021/article/details/126455572
 *
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/12 23:27
 */
@Service
@Slf4j
public class Mp3ServiceImpl implements Mp3Service {
    @Autowired
    private MyConfig myConfig;

    @Override
    public FileMp3InfoVo parser(String name){
        FileMp3InfoVo fileMp3InfoVo = null;

        String file = myConfig.getMp3Dir() + File.separator + name;
        try {
            String[] titleArray = name.split("#");
            // 标题
            String title = titleArray[0];
            // 作者
            String author = titleArray[1];
            int dotIndex = author.lastIndexOf(".");
            author = author.substring(0, dotIndex);

            long durationSecond = VideoUtil.getDuration(file);

            fileMp3InfoVo = new FileMp3InfoVo();
            fileMp3InfoVo.setTitle(title);
            fileMp3InfoVo.setAuthor(author);
            fileMp3InfoVo.setFileName(name);
            fileMp3InfoVo.setDurationSecond(durationSecond);
        } catch (IOException e) {
            log.info("解析文件失败:{}，文件的格式 title#author", file, e);
        }

        return fileMp3InfoVo;
    }

}
