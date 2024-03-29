package com.hry.project.capcut.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.content.enums.LyricTruncatedModeEnum;
import com.hry.project.capcut.content.template.impl.TemplateProcessV1Msg;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.pojo.TemplateConfigV1Vo;
import com.hry.project.capcut.pojo.TemplateReturnInfoV1Vo;
import com.hry.project.capcut.service.ContextService;
import com.hry.project.capcut.service.Mp3Service;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:26
 */
@Component
@Slf4j
public class TemplateV1ServiceImpl extends BaseTemplateV1Service {
    @Autowired
    private TemplateProcessV1Msg templateProcessV1Msg;
    @Autowired
    private Mp3Service mp3Service;
    @Autowired
    private ContextService contextService;
    @Autowired
    private MyConfig myConfig;

    @Override
    public TemplateReturnInfoV1Vo execute(String configFileName,boolean init, LyricTruncatedModeEnum lyricTruncatedModeEnum) {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(myConfig.getConfigDir() + File.separator + configFileName));
        List<CsvRow> rows = data.getRows();
        // 当前值读取第一行
        CsvRow firsRow = rows.get(1);
        log.info(GsonBox.PUBLIC.toJson(firsRow));
        String mp3FileName = firsRow.get(0);
        String picName = firsRow.get(1);
        return execute(mp3FileName, picName, init, lyricTruncatedModeEnum);
    }

    /**
     * 文件
     * @param mp3FileName mp3名称: aaa.mp3
     * @param picName 图片名： abc.jpg
     * @return
     */
    @Override
    public TemplateReturnInfoV1Vo execute(String mp3FileName, String picName, boolean init, LyricTruncatedModeEnum lyricTruncatedModeEnum){
        TemplateConfigV1Vo templateConfigV1Vo = new TemplateConfigV1Vo();

        FileMp3InfoVo mp3InfoVo = mp3Service.parser(mp3FileName);
        // 语音时长
        long durationSecond = mp3InfoVo.getDurationSecond();
        // 文件原始名称
        String fileName = mp3InfoVo.getFileName();
        // 作者
        String author = mp3InfoVo.getAuthor();
        // 标题
        String title = mp3InfoVo.getTitle();

        // 获取文件内容
        String content = getContentFromFile(init, templateConfigV1Vo);
        templateConfigV1Vo.setDuration(durationSecond * 1000000L);

        // pic 信息
        templateConfigV1Vo.setPicName(picName);
//        templateConfigV1Vo.setPicNameWithPath(myConfig.getPicDir() + File.separator + picName);
        // TODO 待修改
        templateConfigV1Vo.setPicNameWithPath("D:\\douyin\\sucai\\tupian" + File.separator + picName);

        // mp3信息
        templateConfigV1Vo.setMp3Name(mp3FileName);
        // TODO 待修改
//        templateConfigV1Vo.setPicNameWithPath(myConfig.getMp3Dir() + File.separator + mp3FileName);
        templateConfigV1Vo.setMp3NameWithPath("D:\\douyin\\sucai\\mp3" + File.separator + mp3FileName);


        templateConfigV1Vo.setMp3Name(mp3FileName);
        templateConfigV1Vo.setAuthor(author);
        templateConfigV1Vo.setTitle(title);
        templateConfigV1Vo.setLyricTruncatedModeEnum(lyricTruncatedModeEnum);

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

    private String getContentFromFile(boolean init, TemplateConfigV1Vo templateConfigV1Vo) {
        String content;
        if(init) {
            // 如果是初始化，则从模板获取
            content = contextService.getInitContentFromFile();
            templateConfigV1Vo.setLyric(false);
        }else {
            // 从工程目录获取
            content = contextService.getSonContentFromFile();
            // 此时应该有歌词
            templateConfigV1Vo.setLyric(true);
        }
        return content;
    }

}
