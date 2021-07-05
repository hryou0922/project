package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionGroupQry;
import com.hry.project.dictation.mapper.QuestionGroupListMapper;
import com.hry.project.dictation.mapper.QuestionGroupMapper;
import com.hry.project.dictation.model.QuestionGroupListModel;
import com.hry.project.dictation.model.QuestionGroupModel;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.service.IQuestionGroupService;
import com.hry.project.dictation.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
@Service
public class QuestionGroupServiceImpl extends ServiceImpl<QuestionGroupMapper, QuestionGroupModel> implements IQuestionGroupService {
    @Autowired
    private QuestionGroupListMapper questionGroupListMapper;

    @Override
    public QuestionGroupModel selectWrodGroupByGroupName(String name) {
        if(!StringUtils.hasText(name)){
            return null;
        }
        QueryWrapper<QuestionGroupModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MyPage<QuestionGroupModel> queryQuestionGroupPage(QuestionGroupQry qry) {
        if(qry == null){
            qry = new QuestionGroupQry();
        }
        QueryWrapper<QuestionGroupModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(qry.getName()) , "name", qry.getName());
        queryWrapper.orderByDesc("create_time");

        Page<QuestionGroupModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<QuestionGroupModel> page = page(pageQry, queryWrapper);

        MyPage<QuestionGroupModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public MyPage<QuestionModel> queryQuestionGroupListPage(QuestionGroupQry qry) {
        if(qry == null){
            qry = new QuestionGroupQry();
        }

        QueryWrapper<QuestionModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(qry.getGroupId() != null, "group_id", qry.getGroupId());
        // 列表
        List<String> wordList = qry.getWordList();
        queryWrapper.in(wordList != null && wordList.size() > 0 , "a.question_id", wordList);

        Page<QuestionModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<QuestionModel> page = questionGroupListMapper.queryWordGroupListPage(pageQry, queryWrapper);
        MyPage<QuestionModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public boolean isQuestionInGroup(long groupId, String questionId) {
        CheckUtil.checkNotEmpty("questionId", questionId);

        QueryWrapper<QuestionGroupListModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true, "group_id", groupId);
        queryWrapper.eq(true, "questionId", questionId);

        if(questionGroupListMapper.selectList(queryWrapper).size() == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void creatTmpQuestionGroup(String groupName, List<String> questionIdList) {
//        CheckUtil.checkNotEmpty("name", groupName);
//        if(questionIdList == null || questionIdList.size() == 0){
//            throw new RuntimeException("每个组里的题目列表不能为空");
//        }
//        // 组名
//        groupName =  groupName + "." + CommonDateUtils.dateTime2String(new Date());
//        // 创建临时组
//        WordGroupModel wordGroupModel = new WordGroupModel();
//        wordGroupModel.setCreateTime(new Date());
//        wordGroupModel.setWordTotal(questionIdList.size());
//        wordGroupModel.setName(groupName);
//        wordGroupModel.setType(WordGroupEnum.TMP_GROUP.getType());
//        baseMapper.insert(wordGroupModel);
//
//        // 创建词语列表
//        for(String word : wordList){
//            WordGroupListModel wordGroupListModel = new WordGroupListModel();
//            wordGroupListModel.setGroupId(wordGroupModel.getId());
//            wordGroupListModel.setWord(word);
//            wordGroupListMapper.insert(wordGroupListModel);
//        }

        throw new RuntimeException("待实现方法");
    }

    @Override
    public void deleteQuestionGroupById(long groupId) {
        throw new RuntimeException("待实现方法");
    }

    @Override
    public void save(QuestionGroupModel entity, List<QuestionGroupListModel> modelList) {
        throw new RuntimeException("待实现方法");
    }

    @Override
    public void updateGroupInfo(Long[] groupIdArray) {
        throw new RuntimeException("待实现方法");
    }

//
//    @Override
//    public void deleteWordGroupById(long groupId){
//        // 删除词语列表
//        QueryWrapper<WordGroupListModel> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(true, "group_id", groupId);
//        wordGroupListMapper.delete(queryWrapper);
//
//        // 删除组
//        baseMapper.deleteById(groupId);
//    }
//
//    @Override
//    public void save(WordGroupModel entity, List<WordGroupListModel> listModelList) {
//        CheckUtil.checkNotEmpty("name", entity.getName());
//        if(listModelList == null || listModelList.size() == 0){
//            throw new RuntimeException("每个组里的词语列表不能为空");
//        }
//        // 组数量
//        int wordTotal = listModelList.size();
//        entity.setWordTotal(wordTotal);
//        entity.setCreateTime(new Date());
//        // 添加记录
//        baseMapper.insert(entity);
//
//        for(WordGroupListModel tmpModel : listModelList){
//            // 补全组id
//            tmpModel.setGroupId(entity.getId());
//            // 添加数据
//            wordGroupListMapper.insert(tmpModel);
//        }
//    }
//
//    @Override
//    public void updateGroupInfo(Long[] groupIdArray){
//        if(groupIdArray != null){
//            for(Long groupId : groupIdArray){
//                if(groupId != null){
//                    // 获取groupId 所有的记录，并进行统计
//                    WordGroupQry wordGroupQry = new WordGroupQry();
//                    wordGroupQry.setGroupId(groupId);
//                    wordGroupQry.setPageSize(Integer.MAX_VALUE);
//                    int total = 0;
//                    int passNum = 0;
//                    int gooNum = 0;
//                    Integer excellentNum = 0;
//
//                    for(WordModel wordModel : queryWordGroupListPage(wordGroupQry).getItems()){
//                        total++;
//                        if(wordModel.getLevel() != null) {
//                            FamiliarLevelEnum level = FamiliarLevelEnum.valueOfType(wordModel.getLevel());
//                            if (level != null) {
//                                switch (level) {
//                                    case PASS:
//                                        passNum++;
//                                        break;
//                                    case GOOD:
//                                        passNum++;
//                                        gooNum++;
//                                        break;
//                                    case EXCELLENT:
//                                        passNum++;
//                                        gooNum++;
//                                        excellentNum++;
//                                        break;
//                                    default:
//                                }
//                            }
//                        }
//                    }
//                    // 更新记录
//                    WordGroupModel updateWordGroupModel = new WordGroupModel();
//                    updateWordGroupModel.setId(groupId);
//                    updateWordGroupModel.setWordTotal(total);
//                    updateWordGroupModel.setPassRate(passNum*100/total);
//                    updateWordGroupModel.setGoodRate(gooNum*100/total);
//                    updateWordGroupModel.setExcellentRate(excellentNum*100/total);
//                    updateWordGroupModel.setResultTime(new Date());
//                    baseMapper.updateById(updateWordGroupModel);
//                }
//            }
//        }
//    }

}
