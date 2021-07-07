package com.hry.project.dictation.controller.question;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpBatchUpdateReq;
import com.hry.project.dictation.dto.req.question.QuestionHisTmpQry;
import com.hry.project.dictation.model.QuestionHisTmpModel;
import com.hry.project.dictation.service.IQuestionHisTmpService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/question-his-tmp")
public class QuestionHisTmpCtl {
    private static final Logger logger = LoggerFactory.getLogger(QuestionHisTmpCtl.class);

    @Autowired
    private IQuestionHisTmpService questionHisTmpService;

    /**
     * 查询列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<QuestionHisTmpModel> list(@ModelAttribute QuestionHisTmpQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return questionHisTmpService.queryPage(qry);
    }

    /**
     * 更新记录
     * @param req
     * @return
     */
    @RequestMapping(value = "batch_update", method = RequestMethod.POST)
    public CommonRsp batchUpdate(@RequestBody QuestionHisTmpBatchUpdateReq req){
        logger.info("收到更新请求: {}", CommonJsonUtils.toJsonString(req));
        questionHisTmpService.batchUpdate(req);
        return  CommonRsp.getOkCommonRsp();
    }

    /**
     * 归档：
     *  将所有记录归档
     * @return
     */
    @RequestMapping(value = "archive",  method = RequestMethod.POST)
    public CommonRsp archive(@RequestBody QuestionHisTmpBatchUpdateReq req){
        logger.info("归档操作");
        questionHisTmpService.archive(req);
        return  CommonRsp.getOkCommonRsp();
    }

}

