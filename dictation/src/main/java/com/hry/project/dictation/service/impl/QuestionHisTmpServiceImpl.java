package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.constant.Constants;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpBatchUpdateReq;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.enums.FamiliarLevelEnum;
import com.hry.project.dictation.mapper.QuestionHisMapper;
import com.hry.project.dictation.mapper.QuestionHisTmpMapper;
import com.hry.project.dictation.model.QuestionHisModel;
import com.hry.project.dictation.model.QuestionHisTmpModel;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.service.IQuestionGroupService;
import com.hry.project.dictation.service.IQuestionHisTmpService;
import com.hry.project.dictation.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
@Service
public class QuestionHisTmpServiceImpl extends ServiceImpl<QuestionHisTmpMapper, QuestionHisTmpModel> implements IQuestionHisTmpService {

    @Autowired
    private QuestionHisTmpMapper questionHisTmpMapper;
    @Autowired
    private QuestionHisMapper questionHisMapper;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IQuestionGroupService questionGroupService;


    @Override
    public MyPage<QuestionHisTmpModel> queryPage(QuestionHisTmpQry qry) {
        QueryWrapper<QuestionHisTmpModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(qry.getTopic()) , "topic", qry.getTopic());
        queryWrapper.eq(qry.getResult() != null, "result", qry.getResult());
        queryWrapper.orderByDesc("create_time");

        Page<QuestionHisTmpModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<QuestionHisTmpModel> page = page(pageQry, queryWrapper);

        MyPage<QuestionHisTmpModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public void batchUpdate(QuestionHisTmpBatchUpdateReq req) {
        int result = req.getResult();
        List<Long> idList = req.getIds();
        if(idList != null){
            if(result == Constants.DELETE){
                // 执行批量删除
                baseMapper.deleteBatchIds(idList);
            }else {
                // 更新正确、错误
                for (Long id : idList) {
                    if (id != null) {
                        QuestionHisTmpModel tmp = new QuestionHisTmpModel();
                        tmp.setId(id);
                        tmp.setResult(result);
                        baseMapper.updateById(tmp);
                    }
                }
            }
        }
    }

    @Override
    public void archive(QuestionHisTmpBatchUpdateReq req) {
        List<QuestionHisTmpModel> list = this.listByIds(req.getIds());
        // 保存
        Set<Long> groupIdSet = new HashSet<>();
        for(QuestionHisTmpModel tmpModel : list){
            Integer tmpResult = tmpModel.getResult();
            long dictationHisModelCreateTime = tmpModel.getCreateTime().getTime();
            String questionId = tmpModel.getQuestionId();
            Long groupId = tmpModel.getGroupId();
            if(groupId != null){
                groupIdSet.add(groupId);
            }
            long id = tmpModel.getId();
            // 本次听写是成功还是失败
            boolean isDitationSucess = (tmpResult != null
                    && tmpResult == Constants.RESULT_SUCCESS) ? true : false;

            // 更新词语表状态
            QuestionModel dbModel = questionService.getById(questionId);
            if(dbModel != null){
                Date oldDate = dbModel.getLevelTime();
                // 是否已经被处理：根据时间进行判断
                if(oldDate == null || oldDate.getTime() < dictationHisModelCreateTime ){
                    // 数据未被处理，则进行处理，词语听写总数和最后状态进行更新
                    Integer total = dbModel.getTotal();
                    total = ((total == null) ? 1 : ++total);
                    dbModel.setTotal(total);
                    Integer newNextLevel = FamiliarLevelEnum.getNextLevel(dbModel.getLevel(), isDitationSucess).getLevel();
                    dbModel.setLevel(newNextLevel);
                    dbModel.setLastResult(tmpResult);
                    dbModel.setLevelTime(tmpModel.getCreateTime());
                    // 更新状态
                    questionService.updateById(dbModel);
                }
            }

            // 写入正式表
            if(questionHisMapper.selectById(id) == null) {
                // 写入正式表
                QuestionHisModel hisModel = new QuestionHisModel();
                hisModel.setId(tmpModel.getId());
                hisModel.setCreateTime(tmpModel.getCreateTime());
                hisModel.setGroupId(tmpModel.getGroupId());
                hisModel.setQuestionId(questionId);
                hisModel.setTopic(tmpModel.getTopic());
                hisModel.setResult(tmpModel.getResult());
                hisModel.setDes(tmpModel.getDes());
                questionHisMapper.insert(hisModel);
            }

            // 删除已经处理的记录
            questionHisTmpMapper.deleteById(tmpModel.getId());
        }

        // 更新组表统计信息
        questionGroupService.updateGroupInfo(groupIdSet.toArray(new Long[0]));
    }

}
