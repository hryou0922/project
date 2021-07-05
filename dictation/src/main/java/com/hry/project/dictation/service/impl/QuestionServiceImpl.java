package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionQry;
import com.hry.project.dictation.mapper.QuestionMapper;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.service.IQuestionService;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, QuestionModel> implements IQuestionService {

    @Override
    public MyPage<QuestionModel> queryPage(QuestionQry qry) {
        QueryWrapper<QuestionModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(qry.getId()), "id", qry.getId());
        queryWrapper.like(StringUtils.hasLength(qry.getTopic()), "topic", qry.getTopic());

        List<Integer> levels = qry.getLevels();
        queryWrapper.in(levels != null && levels.size() !=0, "level", levels);


        Page<QuestionModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<QuestionModel> page = page(pageQry, queryWrapper);

        MyPage<QuestionModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }



}
