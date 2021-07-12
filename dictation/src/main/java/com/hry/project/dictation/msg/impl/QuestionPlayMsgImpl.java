package com.hry.project.dictation.msg.impl;

import com.hry.project.dictation.constant.Constants;
import com.hry.project.dictation.model.QuestionHisTmpModel;
import com.hry.project.dictation.model.QuestionModel;
import com.hry.project.dictation.msg.IQuestionPlayMsg;
import com.hry.project.dictation.msg.IVoicePlayMsg;
import com.hry.project.dictation.service.IQuestionHisTmpService;
import com.hry.project.dictation.service.IQuestionService;
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
 * @date: 2021/7/6 9:52
 */
@Component
public class QuestionPlayMsgImpl implements IQuestionPlayMsg {
    private static final Logger logger = LoggerFactory.getLogger(QuestionPlayMsgImpl.class);

    @Value("${self.voice.rootdir:D:\\db\\voice\\topic}")
    private String rootDir;
    @Value("${self.voice.playnum: 2}")
    private int playNum;
    @Value("${self.voice.sleep: 5}")
    private int sleepSencond;

    @Autowired
    private IQuestionHisTmpService questionHisTmpService;
    @Autowired
    private IQuestionService questionService;

    private IVoicePlayMsg voicePlayMsg;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**.
     * 如果为true，则正在播放
     */
    private final AtomicBoolean play = new AtomicBoolean(false);

    @PostConstruct
    public void init() {
        // 初始化
        voicePlayMsg = new VoicePlayMsgImpl(rootDir, playNum, sleepSencond, false);
    }

    @Override
    public void play(Collection<QuestionModel> questionModels, Long groupId) {
        if(play.get()){
            logger.info("正在播放...");
        }

        if (questionModels != null) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 设置正在播放
                        play.set(true);
                        for (QuestionModel questionModel : questionModels) {
                            if (!play.get()) {
                                logger.info("收到停止播放，停止播放.");
                                break;
                            }
                            if (questionModel != null) {
                                VoiceInfoResultVo voiceInfoResultVo = voicePlayMsg.playOneWord(questionModel.getTopic(), questionModel.getVoiceFile());
                                if(voiceInfoResultVo != null){
                                    // 语音创建成功，更新目录
                                    QuestionModel updateModel = new QuestionModel();
                                    updateModel.setId(questionModel.getId());
                                    updateModel.setVoiceFile(voiceInfoResultVo.getVoiceFilePath());
                                    questionService.updateById(updateModel);
                                }

                                // 临时表
                                QuestionHisTmpModel model = new QuestionHisTmpModel();
                                model.setQuestionId(questionModel.getId());
                                model.setTopic(questionModel.getTopic());
                                model.setGroupId(groupId);
                                model.setCreateTime(new Date());
                                model.setResult(Constants.RESULT_SUCCESS);
                                questionHisTmpService.save(model);
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
