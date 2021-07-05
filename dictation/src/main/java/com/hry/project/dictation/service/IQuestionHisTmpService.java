package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpBatchUpdateReq;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.model.QuestionHisTmpModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
public interface IQuestionHisTmpService extends IService<QuestionHisTmpModel> {

    MyPage<QuestionHisTmpModel> queryPage(QuestionHisTmpQry qry);

    void batchUpdate(QuestionHisTmpBatchUpdateReq req);

    /**
     * 处理临时表记录
     */
    void archive( QuestionHisTmpBatchUpdateReq req);
}
