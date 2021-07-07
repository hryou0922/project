package com.hry.project.dictation.service;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.mapper.QuestionGroupListMapper;
import com.hry.project.dictation.model.QuestionGroupListModel;
import com.hry.project.dictation.model.QuestionGroupModel;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/6 19:28
 */
public class SaveQuestionInDb extends BaseTest {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IQuestionGroupService questionGroupService;
    @Autowired
    private QuestionGroupListMapper questionGroupListMapper;


    @Test
    public void saveWordFromFile(){
        // D:\study\project\dictation\src\main\resources\word\word.csv
        // D:\code_idea\study\project\dictation\target\test-classes\src\main\resources\question\
        String file = "D:\\code_idea\\study\\project\\dictation\\target\\classes\\question\\topic.xlsx";



        ExcelReader reader = ExcelUtil.getReader(file);
        // sheet 的数量
        int sheetCount = reader.getSheetCount();
        // sheet 名称
        List<String> sheetNameList = reader.getSheetNames();

        System.out.println("   " + reader.getSheetCount());

        for(int i = 0; i < sheetCount; i++){
            saveIntoDb(file, sheetNameList.get(i), i);
        }

        // saveIntoDb(file);
    }

    private void saveIntoDb(String file, String sheetName, int sheetNum) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        List<List<Object>> readAll = ExcelUtil.getReader(file, sheetNum).read();
        int newAdd = 0;
        // 跳过第一行
        for(int i= 1; i < readAll.size(); i++){
            List<Object> line = readAll.get(i);
            System.out.println("==" + line + " " + line.size());
            if(line.size() < 2){
                System.out.println("数据量太少，跳过，原始值: " + line);
                continue;
            }
            String model = (String) line.get(0);
            String topic = (String) line.get(1);
            String id =  md5.digestHex(topic);
            String answer = "";
            if(line.size() >= 3) {
                answer = (String) line.get(2);
            }

            String groupName = sheetName + "_" + model;

//                Integer type = null;
//                if(part.length >= 3){
//                    type = Integer.parseInt(part[2]);
//                }
//                String desc = "";
//                if(part.length >= 4) {
//                    desc = part[3];
//                }

            // 添加组
            QuestionGroupModel questionGroupModel = questionGroupService.selectWrodGroupByGroupName(groupName);
            if(questionGroupModel == null) {
                questionGroupModel = new QuestionGroupModel();
                questionGroupModel.setCreateTime(new Date());
                questionGroupModel.setName(groupName);
                questionGroupService.save(questionGroupModel);
            }
            long groupId = questionGroupModel.getId();
            // 添加组题目列表
            if(!questionGroupService.isQuestionInGroup(groupId, id)){
                QuestionGroupListModel wordGroupListModel = new QuestionGroupListModel();
                wordGroupListModel.setGroupId(groupId);
                wordGroupListModel.setQuestionId(id);
                questionGroupListMapper.insert(wordGroupListModel);
            }

            // 添加题目
            QuestionModel questionModel = new QuestionModel();
            // wordModel.setType(type);
            questionModel.setTopic(topic);
            questionModel.setAnswer(answer);
            questionModel.setId(id);
            System.out.println(i + " " + CommonJsonUtils.toJsonString(questionModel));

            QuestionModel oldWordModel = questionService.getById(id);
            if(oldWordModel == null){
                questionService.save(questionModel);
                newAdd ++;
            }

        }
        System.out.println("本次新增记录: " + newAdd);

    }


}
