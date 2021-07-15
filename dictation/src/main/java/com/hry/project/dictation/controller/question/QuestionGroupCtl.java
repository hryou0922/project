package com.hry.project.dictation.controller.question;


import com.hry.project.dictation.controller.word.DictationHisTmpCtl;
import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionGroupQry;
import com.hry.project.dictation.model.QuestionGroupModel;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.msg.IQuestionPlayMsg;
import com.hry.project.dictation.service.IQuestionGroupService;
import com.hry.project.dictation.utils.CheckUtil;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/question-group")
public class QuestionGroupCtl {
    private static final Logger logger = LoggerFactory.getLogger(DictationHisTmpCtl.class);

    @Autowired
    private IQuestionGroupService questionGroupService;
    @Autowired
    private IQuestionPlayMsg questionPlayMsg;

    /**
     * 查询组列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<QuestionGroupModel> list(@ModelAttribute QuestionGroupQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return questionGroupService.queryQuestionGroupPage(qry);
    }

    /**
     * 查询组里的词语列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "question-list")
    public MyPage<QuestionModel> listQuestion(@RequestBody QuestionGroupQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return questionGroupService.queryQuestionGroupListPage(qry);
    }

    /**
     * 创建临时听写组
     * @param qry
     * @return
     */
    @RequestMapping(value = "create-tmp-question-group")
    public CommonRsp createTmpQuestionGroup(@RequestBody QuestionGroupQry qry){
        logger.info("创建临时听写组:{}", CommonJsonUtils.toJsonString(qry));
        // TODO 后面创建组名称
        String groupName = qry.getName();
        if(!StringUtils.hasLength(groupName)){
            groupName = "临时组";
        }
        questionGroupService.creatTmpQuestionGroup(groupName, qry.getQuestionList());
        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 删除组
     * @param qry
     * @return
     */
    @RequestMapping(value = "delete-question-group")
    public CommonRsp deleteQuestionGroup(@RequestBody QuestionGroupQry qry){
        logger.info("删除组:{}", CommonJsonUtils.toJsonString(qry));
        Long groupId = qry.getGroupId();
        CheckUtil.checkNotNull("groupId", groupId);
        questionGroupService.deleteQuestionGroupById(groupId);
        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 播放
     * @param qry
     * @return
     */
    @RequestMapping(value = "play")
    public CommonRsp play(@RequestBody QuestionGroupQry qry){
        logger.info("收到play请求:{}", CommonJsonUtils.toJsonString(qry));

        // 播放全部符合要求的内容
        MyPage<QuestionModel> myPage = questionGroupService.queryQuestionGroupListPage(qry);
        if (myPage != null) {
            Collection<QuestionModel> iterms = myPage.getItems();
            questionPlayMsg.play(iterms, qry.getGroupId());
        }

        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 停止播放
     * @return
     */
    @RequestMapping(value = "stop", method = RequestMethod.GET)
    public CommonRsp stopPlay(){
        questionPlayMsg.stop();
        return CommonRsp.getOkCommonRsp();
    }

}

