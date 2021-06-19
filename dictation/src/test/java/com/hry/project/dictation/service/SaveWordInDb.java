package com.hry.project.dictation.service;

import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.mapper.WordGroupListMapper;
import com.hry.project.dictation.model.WordGroupListModel;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SaveWordInDb extends BaseTest {
    @Autowired
    private IWordService wordService;
    @Autowired
    private IWordGroupService wordGroupService;
    @Autowired
    private WordGroupListMapper wordGroupListMapper;

    @Test
    public void saveWordFromFileTmp(){
        // D:\study\project\dictation\src\main\resources\word\word.csv
        String file = "src\\main\\resources\\word\\word_tmp.csv";
        saveIntoDb(file);
    }

    @Test
    public void saveWordFromFile(){
        // D:\study\project\dictation\src\main\resources\word\word.csv
        String file = "src\\main\\resources\\word\\word.csv";
        saveIntoDb(file);
    }



    private void saveIntoDb(String file) {
        try {
            List<String> lines = FileUtils.readLines(new File(file), "utf-8");
            int newAdd = 0;
            // 跳过第一行
            for(int i= 1; i < lines.size(); i++){
                String line = lines.get(i);
                if(!org.springframework.util.StringUtils.hasText(line)){
                    break;
                }
                String[] part = line.split("\\s+");
                String groupName = part[0];
                String word = part[1];

                Integer type = null;
                if(part.length >= 3){
                    type = Integer.parseInt(part[2]);
                }
                String desc = "";
                if(part.length >= 4) {
                    desc = part[3];
                }

                // 添加组
                WordGroupModel wordGroupModel = wordGroupService.selectWrodGroupByGroupName(groupName);
                if(wordGroupModel == null) {
                    wordGroupModel = new WordGroupModel();
                    wordGroupModel.setCreateTime(new Date());
                    wordGroupModel.setName(groupName);
                    wordGroupService.save(wordGroupModel);
                }
                long groupId = wordGroupModel.getId();
                // 添加组词语列表
                if(!wordGroupService.isWordInGroup(groupId, word)){
                    WordGroupListModel wordGroupListModel = new WordGroupListModel();
                    wordGroupListModel.setGroupId(groupId);
                    wordGroupListModel.setWord(word);
                    wordGroupListMapper.insert(wordGroupListModel);
                }

                // 添加词语表
                WordModel wordModel = new WordModel();
                wordModel.setType(type);
                wordModel.setWord(word);
                wordModel.setVoiceFile(desc);
                System.out.println(i + " " + CommonJsonUtils.toJsonString(wordModel));

                WordModel oldWordModel = wordService.selectByWord(word);
                if(oldWordModel == null){
                    wordService.save(wordModel);
                    newAdd ++;
                }

            }
            System.out.println("本次新增记录: " + newAdd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
