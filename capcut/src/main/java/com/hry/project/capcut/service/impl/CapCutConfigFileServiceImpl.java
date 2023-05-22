package com.hry.project.capcut.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import com.hry.project.capcut.config.MyConfig;
import com.hry.project.capcut.pojo.ConfigRecordVo;
import com.hry.project.capcut.service.CapCutConfigFileService;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/19 21:40
 */
@Service
@Slf4j
public class CapCutConfigFileServiceImpl implements CapCutConfigFileService {
    @Autowired
    private MyConfig myConfig;

    @Override
    public void execute(){
        // mp3
        List<String> mp3NameList = FileUtil.listFileNames(myConfig.getMp3Dir());
        // pic
        List<String> picNameList = FileUtil.listFileNames(myConfig.getPicDir());
        if(mp3NameList.size() == 0 || picNameList.size() == 0){
            log.warn("mp3目录={}或pic目录={}没有文件，直接结束", myConfig.getMp3Dir(), myConfig.getPicDir());
            return;
        }

        List<ConfigRecordVo> configRecordVoList = new ArrayList<>(mp3NameList.size());
        String lastPicName = "";
        for(String tmpMp3Name : mp3NameList){
            ConfigRecordVo configRecordVo = new ConfigRecordVo();
            // test.txt
            configRecordVo.setMp3Name(tmpMp3Name);
            // pic
            String tmpPicName;
            if(picNameList.size() > 0) {
                tmpPicName = picNameList.remove(0);
                lastPicName = tmpPicName;
            }else {
                tmpPicName = lastPicName;
                log.warn("mp3=[{}] 由于图片不足，使用最后一个图片{}", tmpMp3Name, tmpPicName);
            }
            configRecordVo.setPicName(tmpPicName);
            configRecordVoList.add(configRecordVo);
        }

        // 循环保存
        // 起始值
        int startIndex = 1;
        for(ConfigRecordVo configRecordVo : configRecordVoList){
            String fileConfigCsv = myConfig.getConfigDir() + File.separator + startIndex + ".txt";
            CsvWriter writer = CsvUtil.getWriter( fileConfigCsv
                    , CharsetUtil.CHARSET_UTF_8);
            writer.write(
                    new String[]{"mp3名称", "图片名称"},
                    new String[]{configRecordVo.getMp3Name(), configRecordVo.getPicName()}
                );
            writer.close();
            log.info("保存文件{}的内容{}", GsonBox.PUBLIC.toJson(configRecordVo), fileConfigCsv);
            startIndex++;
        }
    }


}
