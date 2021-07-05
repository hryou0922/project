package com.hry.project.dictation.msg.impl;

import com.hry.project.dictation.constant.Constants;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.msg.VoicePlayMsg;
import com.hry.project.dictation.service.IDictationHisTmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 19:23
 */
@Component
public class WordPlayMsgImpl implements IWordPlayMsg {
    private static final Logger logger = LoggerFactory.getLogger(WordPlayMsgImpl.class);

    @Autowired
    private IDictationHisTmpService dictationHisTmpService;

    @Autowired
    private VoicePlayMsg voicePlayMsg;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**.
     * 如果为true，则正在播放
     */
    private final AtomicBoolean play = new AtomicBoolean(false);


    @Override
    public void play(Collection<WordModel> iterms, Long groupId) {
        if(play.get()){
            logger.info("正在播放...");
        }

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
                                model.setGroupId(groupId);
                                model.setCreateTime(new Date());
                                model.setResult(Constants.RESULT_SUCCESS);
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

    @Override
    public void stop() {
        play.set(false);
        logger.info("收到停止播放请求");
    }
}
