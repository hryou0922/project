package com.hry.project.dictation.service.impl;

import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.model.WordGroupListModel;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 17:30
 */
public class WordGroupServiceImplTest extends BaseTest {
    @Autowired
    private WordGroupServiceImpl wordGroupService;

    @Test
    public void add(){
        WordGroupModel pMode = new WordGroupModel();
        pMode.setName("临时");

        List<WordGroupListModel> wordList = new ArrayList<>();
        WordGroupListModel wordGroupListModel = new WordGroupListModel();
        wordGroupListModel.setWord("黄莺");
        wordList.add(wordGroupListModel);
        wordGroupListModel = new WordGroupListModel();
        wordGroupListModel.setWord("可靠");
        wordList.add(wordGroupListModel);

        wordGroupService.save(pMode, wordList);
    }

    @Test
    public void queryWordGroupListPage(){
        MyPage<WordModel> page = wordGroupService.queryWordGroupListPage(null);
        System.out.println(CommonJsonUtils.toJsonString(page));
    }

    @Test
    public void queryWordGroupPage(){
        MyPage<WordGroupModel> page = wordGroupService.queryWordGroupPage(null);
        System.out.println(CommonJsonUtils.toJsonString(page));
    }
}
