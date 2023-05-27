package com.hry.project.capcut.service.impl;

import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.content.template.impl.TemplateProcessV1Msg;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.pojo.TemplateConfigV1Vo;
import com.hry.project.capcut.pojo.TemplateReturnInfoV1Vo;
import com.hry.project.capcut.service.ContextService;
import com.hry.project.capcut.service.Mp3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:26
 */
@Component
public class TemplateV1ServiceImpl extends BaseTemplateV1Service {
    @Autowired
    private TemplateProcessV1Msg templateProcessV1Msg;
    @Autowired
    private Mp3Service mp3Service;
    @Autowired
    private ContextService contextService;
    @Autowired
    private MyConfig myConfig;

    /**
     * 文件
     * @param mp3FileName mp3名称: aaa.mp3
     * @param picName 图片名： abc.jpg
     * @return
     */
    @Override
    public TemplateReturnInfoV1Vo execute(String mp3FileName, String picName){
        FileMp3InfoVo mp3InfoVo = mp3Service.parser(mp3FileName);
        // 语音时长
        long durationSecond = mp3InfoVo.getDurationSecond();
        // 文件原始名称
        String fileName = mp3InfoVo.getFileName();
        // 作者
        String author = mp3InfoVo.getAuthor();
        // 标题
        String title = mp3InfoVo.getTitle();

        String content = contextService.getContentFromFile();

        TemplateConfigV1Vo templateConfigV1Vo = new TemplateConfigV1Vo();
        templateConfigV1Vo.setMp3Duration(durationSecond * 1000000L);
        templateConfigV1Vo.setPicName(picName);
//        templateConfigV1Vo.setPicNameWithPath(myConfig.getPicDir() + File.separator + picName);
        // TODO 待修改
        templateConfigV1Vo.setPicNameWithPath("D:\\douyin\\sucai\\tupian" + File.separator + picName);
        templateConfigV1Vo.setMp3Name(mp3FileName);
        templateConfigV1Vo.setAuthor(author);
        templateConfigV1Vo.setTitle(title);
        String newContent = templateProcessV1Msg.execute(content, templateConfigV1Vo);
        contextService.saveContent2File(newContent);

        // 返回内容
        TemplateReturnInfoV1Vo templateReturnInfoV1Vo = new TemplateReturnInfoV1Vo();
        templateReturnInfoV1Vo.setDurationSecond(durationSecond);
        templateReturnInfoV1Vo.setFileName(fileName);
        templateReturnInfoV1Vo.setAuthor(author);
        templateReturnInfoV1Vo.setTitle(title);
        return templateReturnInfoV1Vo;
    }
}
