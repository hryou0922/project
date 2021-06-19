package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordGroupQry;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.service.IWordGroupService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public MyPage<WordModel> listWord(@ModelAttribute WordGroupQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return wordGroupService.queryWordGroupListPage(qry);
    }

    /**
     * 播放
     * @param qry
     * @return
     */
    @RequestMapping(value = "play")
    public CommonRsp play(@ModelAttribute WordGroupQry qry){
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

