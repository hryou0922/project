package com.hry.project.dictation.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hry.project.dictation.model.QuestionGroupListModel;
import com.hry.project.dictation.model.QuestionModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
public interface QuestionGroupListMapper extends BaseMapper<QuestionGroupListModel> {
    /**
     * 查询组里的词语列表
     * @param page
     * @param wrapper
     * @return
     */
    @Select("select * from question_group_list a JOIN question b on a.question_id = b.id ${ew.customSqlSegment}")
    Page<QuestionModel> queryWordGroupListPage(Page<QuestionModel> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
