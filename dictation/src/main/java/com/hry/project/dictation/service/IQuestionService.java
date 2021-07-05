package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionQry;
import com.hry.project.dictation.model.QuestionModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
public interface IQuestionService extends IService<QuestionModel> {

    MyPage<QuestionModel> queryPage(QuestionQry qry);
}
