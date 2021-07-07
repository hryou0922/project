package com.hry.project.dictation.controller.question;


import com.hry.project.dictation.controller.word.DictationHisTmpCtl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.model.QuestionHisModel;
import com.hry.project.dictation.service.IQuestionHisService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/question-his")
public class QuestionHisCtl {
    private static final Logger logger = LoggerFactory.getLogger(DictationHisTmpCtl.class);

    @Autowired
    private IQuestionHisService questionHisService;

    /**
     * 查询列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<QuestionHisModel> list(@ModelAttribute QuestionHisTmpQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return questionHisService.queryPage(qry);
    }

}

