package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordQry;
import com.hry.project.dictation.mapper.WordMapper;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IWordService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, WordModel> implements IWordService {
    @Override
    public WordModel selectByWord(String word) {
        if(!StringUtils.hasText(word)){
            return null;
        }
        QueryWrapper<WordModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("word", word);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MyPage<WordModel> queryPage(WordQry qry) {
        QueryWrapper<WordModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(qry.getGrade() != null , "grade", qry.getGrade());
        queryWrapper.eq(qry.getUnit() != null, "unit", qry.getUnit());
        queryWrapper.like(StringUtils.hasLength(qry.getArticle()), "article", qry.getArticle());
        queryWrapper.like(StringUtils.hasLength(qry.getWord()), "word", qry.getWord());

        Page<WordModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<WordModel> page = page(pageQry, queryWrapper);

        MyPage<WordModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }
}
