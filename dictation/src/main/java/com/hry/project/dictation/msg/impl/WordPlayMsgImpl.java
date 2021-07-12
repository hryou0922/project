package com.hry.project.dictation.msg.impl;

import com.hry.project.dictation.constant.Constants;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.msg.IVoicePlayMsg;
import com.hry.project.dictation.msg.IWordPlayMsg;
import com.hry.project.dictation.service.IDictationHisTmpService;
import com.hry.project.dictation.service.IWordService;
import com.hry.project.dictation.speech.baidu.VoiceInfoResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @Value("${self.voice.rootdir:D:\\db\\voice}")
    private String rootDir;
    @Value("${self.voice.playnum: 4}")
    private int playNum;
    @Value("${self.voice.sleep: 5}")
    private int sleepSencond;

    @Autowired
    private IDictationHisTmpService dictationHisTmpService;
    @Autowired
    private IWordService wordService;

    private IVoicePlayMsg voicePlayMsg;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**.
     * 如果为true，则正在播放
     */
    private final AtomicBoolean play = new AtomicBoolean(false);

    @PostConstruct
    public void init() {
        // 初始化
        voicePlayMsg = new VoicePlayMsgImpl(rootDir, playNum, sleepSencond, true);
    }


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
                                VoiceInfoResultVo voiceInfoResultVo = voicePlayMsg.playOneWord(wordModel.getWord(), wordModel.getVoiceFile());
                                if(voiceInfoResultVo != null){
                                    // 语音创建成功，更新目录
                                    WordModel updateModel = new WordModel();
                                    updateModel.setId(wordModel.getId());
                                    updateModel.setVoiceFile(voiceInfoResultVo.getVoiceFilePath());
                                    wordService.updateById(updateModel);
                                }

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
