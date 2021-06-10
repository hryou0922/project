package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordQry;
import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.VoicePlayMsg;
import com.hry.project.dictation.service.DictationHisTmpService;
import com.hry.project.dictation.service.WordService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private WordService wordService;
    @Autowired
    private DictationHisTmpService dictationHisTmpService;
    @Autowired
    private VoicePlayMsg voicePlayMsg;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**.
     * 如果为true，则正在播放
     */
    private final AtomicBoolean play = new AtomicBoolean(false);

    @RequestMapping(value = "list")
    public MyPage<WordModel> list(@ModelAttribute WordQry qry){
        logger.info("收到登录请求:{}", CommonJsonUtils.toJsonString(qry));

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
        if(play.get()){
            logger.info("正在播放...");
            return CommonRsp.getOkCommonRsp();
        }

            // 播放全部符合要求的内容
            MyPage<WordModel> myPage = wordService.queryPage(qry);
            if (myPage != null) {
                Collection<WordModel> iterms = myPage.getItems();
                if (iterms != null) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // 设置正在播放
                                play.set(true);
                                for (WordModel wordModel : iterms) {
                                    if (!play.get()) {
                                        logger.info("收到停止播放，停止播放.");
                                        break;
                                    }
                                    if (wordModel != null) {
                                        voicePlayMsg.playOneWord(wordModel);
                                        // dictationHisTmpService
                                        DictationHisTmpModel model = new DictationHisTmpModel();
                                        model.setWord(wordModel.getWord());
                                        model.setCreateTime(new Date());
                                        model.setResult(DictationHisModel.DICTATION_RESULT_SUCCESS);
                                        dictationHisTmpService.save(model);
                                    }
                                }
                            }finally {
                                play.set(false);
                            }
                        }
                    });

                }
            }

        return CommonRsp.getOkCommonRsp();
    }

    /**
     * 停止播放
     * @return
     */
    @RequestMapping(value = "stop", method = RequestMethod.GET)
    public CommonRsp stopPlay(){
        play.set(false);
        logger.info("收到停止播放请求");
        return CommonRsp.getOkCommonRsp();
    }
}

