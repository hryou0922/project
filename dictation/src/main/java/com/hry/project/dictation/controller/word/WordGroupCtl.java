package com.hry.project.dictation.controller.word;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordGroupQry;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.service.IWordGroupService;
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
@RequestMapping("/word-group")
public class WordGroupCtl {
    private static final Logger logger = LoggerFactory.getLogger(DictationHisTmpCtl.class);

    @Autowired
    private IWordGroupService wordGroupService;
    @Autowired
    private IWordPlayMsg wordPlayMsg;

    /**
     * 查询组列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<WordGroupModel> list(@ModelAttribute WordGroupQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return wordGroupService.queryWordGroupPage(qry);
    }

    /**
     * 查询组里的词语列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "word-list")
    public MyPage<WordModel> listWord(@RequestBody WordGroupQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return wordGroupService.queryWordGroupListPage(qry);
    }

    /**
     * 创建临时听写组
     * @param qry
     * @return
     */
    @RequestMapping(value = "create-tmp-word-group")
    public CommonRsp createTmpWordGroup(@RequestBody WordGroupQry qry){
        logger.info("创建临时听写组:{}", CommonJsonUtils.toJsonString(qry));
        // TODO 后面创建组名称
        String groupName = qry.getName();
        if(!StringUtils.hasLength(groupName)){
            groupName = "临时组";
        }
        wordGroupService.creatTmpWordGroup(groupName, qry.getWordList());
        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 删除组
     * @param qry
     * @return
     */
    @RequestMapping(value = "delete-word-group")
    public CommonRsp deleteWordGroup(@RequestBody WordGroupQry qry){
        logger.info("删除组:{}", CommonJsonUtils.toJsonString(qry));
        Long groupId = qry.getGroupId();
        CheckUtil.checkNotNull("groupId", groupId);
        wordGroupService.deleteWordGroupById(groupId);
        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 播放
     * @param qry
     * @return
     */
    @RequestMapping(value = "play")
    public CommonRsp play(@RequestBody WordGroupQry qry){
        logger.info("收到play请求:{}", CommonJsonUtils.toJsonString(qry));

        // 播放全部符合要求的内容
        MyPage<WordModel> myPage = wordGroupService.queryWordGroupListPage(qry);
        if (myPage != null) {
            Collection<WordModel> iterms = myPage.getItems();
            wordPlayMsg.play(iterms, qry.getGroupId());
        }

        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 停止播放
     * @return
     */
    @RequestMapping(value = "stop", method = RequestMethod.GET)
    public CommonRsp stopPlay(){
        wordPlayMsg.stop();
        return CommonRsp.getOkCommonRsp();
    }

}

