package com.hry.project.dictation.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hry.project.dictation.model.WordGroupListModel;
import com.hry.project.dictation.model.WordModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hry
 * @since 2021-06-15
 */
public interface WordGroupListMapper extends BaseMapper<WordGroupListModel> {

    /**
     * 查询组里的词语列表
     * @param page
     * @param wrapper
     * @return
     */
    @Select("select * from word_group_list a JOIN word b on a.word = b.word ${ew.customSqlSegment}")
    Page<WordModel> queryWordGroupListPage(Page<WordModel> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
