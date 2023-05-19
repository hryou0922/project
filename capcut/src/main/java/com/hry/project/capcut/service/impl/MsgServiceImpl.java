package com.hry.project.capcut.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.pojo.FileMp3InfoVo;
import com.hry.project.capcut.service.ContextService;
import com.hry.project.capcut.service.Mp3Service;
import com.hry.project.capcut.service.MsgService;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:38
 */
@Service
@Slf4j
public class MsgServiceImpl implements MsgService {
    @Autowired
    private Mp3Service mp3Service;
    @Autowired
    private ContextService contextService;
    @Autowired
    private MyConfig myConfig;

    @Override
    public FileMp3InfoVo execute(String mp3FileName, String picName){
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

        // 开始替换
        String newContent = content;
        // 替换文本名称
        newContent = newContent.replaceAll("pexels-pixabay-290548.jpg", picName);

        // 时长替换
        newContent = newContent.replaceAll("18533333", (durationSecond * 1000000L) + "");
        // mp3文件： 小城故事邓丽君.mp3 158066666
        newContent = newContent.replaceAll("小城故事邓丽君.mp3", mp3FileName);
        newContent = newContent.replaceAll("158066666",(durationSecond * 1000000L) + "");
        // 作者替换
        newContent = newContent.replaceAll("一字之差", title);
        // 标题替换
        newContent = newContent.replaceAll("薛明媛", author);

        // 替换
        contextService.saveContent2File(newContent);

        return mp3InfoVo;
    }

    @Override
    public FileMp3InfoVo execute(String configFileName) {
        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(myConfig.getConfigDir() + File.separator + configFileName));
        List<CsvRow> rows = data.getRows();
        //遍历行
//        for (CsvRow csvRow : rows) {
//            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
//            Console.log(csvRow.getRawList());
//        }
        // 当前值读取第一行
        CsvRow firsRow = rows.get(1);
        log.info(GsonBox.PUBLIC.toJson(firsRow));
        String mp3FileName = firsRow.get(0);
        String picName = firsRow.get(1);
        return execute(mp3FileName, picName);
    }
}
