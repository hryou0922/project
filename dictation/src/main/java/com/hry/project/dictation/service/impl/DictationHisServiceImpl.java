package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.mapper.DictationHisMapper;
import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.service.IDictationHisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@Service
public class DictationHisServiceImpl extends ServiceImpl<DictationHisMapper, DictationHisModel> implements IDictationHisService {


    @Override
    public MyPage<DictationHisModel> queryPage(DictationHisTmpQry qry) {
        QueryWrapper<DictationHisModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(qry.getWord()) , "word", qry.getWord());
        queryWrapper.eq(qry.getResult() != null, "result", qry.getResult());
        queryWrapper.orderByDesc("create_time");

        Page<DictationHisModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<DictationHisModel> page = page(pageQry, queryWrapper);

        MyPage<DictationHisModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public MyPage<DictationHisModel> queryPageOrderWordAsc(DictationHisTmpQry qry) {
        QueryWrapper<DictationHisModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time");

        Page<DictationHisModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<DictationHisModel> page = page(pageQry, queryWrapper);
        MyPage<DictationHisModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

}
