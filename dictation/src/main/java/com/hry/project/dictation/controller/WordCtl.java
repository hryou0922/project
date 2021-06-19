package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordQry;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.service.IWordService;
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
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/word")
public class WordCtl {
    private static final Logger logger = LoggerFactory.getLogger(WordCtl.class);

    @Autowired
    private IWordService wordService;
    @Autowired
    private IWordPlayMsg wordPlayMsg;


    @RequestMapping(value = "list")
    public MyPage<WordModel> list(@ModelAttribute WordQry qry){
        logger.info("收到请求:{}", CommonJsonUtils.toJsonString(qry));

        return wordService.queryPage(qry);
    }

    /**
     * 播放
     * @param qry
     * @return
     */
    @RequestMapping(value = "play")
    public CommonRsp play(@ModelAttribute WordQry qry){
        logger.info("收到play请求:{}", CommonJsonUtils.toJsonString(qry));

        // 播放全部符合要求的内容
        MyPage<WordModel> myPage = wordService.queryPage(qry);
        if (myPage != null) {
            Collection<WordModel> iterms = myPage.getItems();
            wordPlayMsg.play(iterms, null);
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

