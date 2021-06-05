package com.hry.project.dictation.service;

import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveWordInDb extends BaseTest {
    @Autowired
    private WordService wordService;

    @Test
    public void saveWordFromFile(){
        // D:\study\project\dictation\src\main\resources\word\word.csv
        String file = "src\\main\\resources\\word\\word.csv";
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
                int grade = Integer.parseInt(part[0]);
                int unit = Integer.parseInt(part[1]);
                String article = part[2];
                String word = part[3];

                String desc = "";
                if(part.length >= 5) {
                    desc = part[4];
                }

                WordModel wordModel = new WordModel();
                wordModel.setGrade(grade);
                wordModel.setUnit(unit);
                wordModel.setArticle(article);
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
