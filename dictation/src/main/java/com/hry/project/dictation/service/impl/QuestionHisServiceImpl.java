package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.mapper.QuestionHisMapper;
import com.hry.project.dictation.model.QuestionHisModel;
import com.hry.project.dictation.service.IQuestionHisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
@Service
public class QuestionHisServiceImpl extends ServiceImpl<QuestionHisMapper, QuestionHisModel> implements IQuestionHisService {

    @Override
    public MyPage<QuestionHisModel> queryPage(QuestionHisTmpQry qry) {
        QueryWrapper<QuestionHisModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(qry.getTopic()) , "topic", qry.getTopic());
        queryWrapper.eq(qry.getResult() != null, "result", qry.getResult());
        queryWrapper.orderByDesc("create_time");

        Page<QuestionHisModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<QuestionHisModel> page = page(pageQry, queryWrapper);

        MyPage<QuestionHisModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public MyPage<QuestionHisModel> queryPageOrderAsc(QuestionHisTmpQry qry) {
        QueryWrapper<QuestionHisModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");

        Page<QuestionHisModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<QuestionHisModel> page = page(pageQry, queryWrapper);
        MyPage<QuestionHisModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }
}
