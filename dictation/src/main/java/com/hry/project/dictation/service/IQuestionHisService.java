package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.model.QuestionHisModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
public interface IQuestionHisService extends IService<QuestionHisModel> {

    MyPage<QuestionHisModel> queryPage(QuestionHisTmpQry qry);

    /**
     * 根据升序读取词语表
     * @param qry
     * @return
     */
    MyPage<QuestionHisModel> queryPageOrderAsc(QuestionHisTmpQry qry);
}
