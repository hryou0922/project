package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.mapper.WordMapper;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.WordService;
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
public class WordServiceImpl extends ServiceImpl<WordMapper, WordModel> implements WordService {
    @Override
    public WordModel selectByWord(String word) {
        if(!StringUtils.hasText(word)){
            return null;
        }
        QueryWrapper<WordModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("word", word);
        return baseMapper.selectOne(queryWrapper);
    }
}
