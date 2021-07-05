package com.hry.project.dictation.controller.question;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionQry;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.service.IQuestionService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/question")
public class QuestionCtl {
    private static final Logger logger = LoggerFactory.getLogger(QuestionCtl.class);

    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IWordPlayMsg wordPlayMsg;


    @RequestMapping(value = "list")
    public MyPage<QuestionModel> list(@RequestBody QuestionQry qry){
        logger.info("收到请求:{}", CommonJsonUtils.toJsonString(qry));
        return questionService.queryPage(qry);
    }

    /**
     * 播放
     * @param qry
     * @return
     */
    @RequestMapping(value = "play")
    public CommonRsp play(@RequestBody QuestionQry qry){
        logger.info("收到play请求:{}", CommonJsonUtils.toJsonString(qry));

        // 播放全部符合要求的内容
        MyPage<QuestionModel> myPage = questionService.queryPage(qry);
        if (myPage != null) {
            Collection<QuestionModel> iterms = myPage.getItems();
            // TODO 增加播放代码
            // wordPlayMsg.play(iterms, null);
        }

        return CommonRsp.getOkCommonRsp();
    }

//    /**
//     * 停止播放
//     * @return
//     */
//    @RequestMapping(value = "stop", method = RequestMethod.GET)
//    public CommonRsp stopPlay(){
//        wordPlayMsg.stop();
//        return CommonRsp.getOkCommonRsp();
//    }
}

