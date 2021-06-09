package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.mapper.DictationHisTmpMapper;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.service.DictationHisTmpService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@Service
public class DictationHisTmpServiceImpl extends ServiceImpl<DictationHisTmpMapper, DictationHisTmpModel> implements DictationHisTmpService {

    @Override
    public MyPage<DictationHisTmpModel> queryPage(DictationHisTmpQry qry) {
        QueryWrapper<DictationHisTmpModel> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(qry.getGrade() != null , "grade", qry.getGrade());
//        queryWrapper.eq(qry.getUnit() != null, "unit", qry.getUnit());
//        queryWrapper.like(StringUtils.hasLength(qry.getArticle()), "article", qry.getArticle());

        Page<DictationHisTmpModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<DictationHisTmpModel> page = page(pageQry, queryWrapper);

        MyPage<DictationHisTmpModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

}
