package com.hry.project.dictation.msg.impl;

import com.hry.project.dictation.msg.IVoicePlayMsg;
import com.hry.project.dictation.speech.baidu.BaiduText2VoiceMsg;
import com.hry.project.dictation.speech.baidu.VoiceInfoResultVo;
import com.hry.project.dictation.utils.VoiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * 语音播放
 */
public class VoicePlayMsgImpl implements IVoicePlayMsg {
    private static final Logger logger = LoggerFactory.getLogger(VoicePlayMsgImpl.class);

    private String rootDir;
    private int playNum;
    private int sleepSencond;
    private boolean isPerWordSleepTime;

    private BaiduText2VoiceMsg baiduText2VoiceMsg;

    /**
     *
     * @param rootDir
     * @param playNum
     * @param sleepSencond
     * @param isPerWordSleepTime 如果true，则播放一个词语，则每个字，sleepSencond；如果false，则播放无论多个字，则休息sleepSencond
     */
    public VoicePlayMsgImpl(String rootDir, int playNum, int sleepSencond, boolean isPerWordSleepTime) {
        this.baiduText2VoiceMsg = new BaiduText2VoiceMsg(rootDir);
        this.rootDir = rootDir;
        this.playNum = playNum;
        this.sleepSencond = sleepSencond;
    }

    @Override
    public VoiceInfoResultVo playOneWord(String text, String voiceFile, String relativeDir){
        if(!StringUtils.hasLength(text)){
            return null;
        }

        VoiceInfoResultVo voiceInfoResultVo = null;

        if(!StringUtils.hasText(voiceFile)){
            if(!StringUtils.hasLength(relativeDir)){
                relativeDir = "/";
            }
            // 文本转换为语音文件
            voiceInfoResultVo = baiduText2VoiceMsg.text2wav(text, relativeDir);
            voiceFile = voiceInfoResultVo.getVoiceFilePath();
        }
        if(StringUtils.hasText(voiceFile)){
            String file = rootDir + File.separator + voiceFile;
            logger.info("文本=[{}]，路径=[{}]", text, file);
            int t = playNum;
            while(t-- > 0 ) {
                VoiceUtils.play(file);
            }
        }

        try {
            Thread.sleep(getSleepTime(text));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return voiceInfoResultVo;
    }

    /**
     * 获取播放一个词语休息时间
     * @param text
     * @return
     */
    private int getSleepTime(String text) {
        if(isPerWordSleepTime) {
            return text.length() * sleepSencond * 1000;
        }else {
            return sleepSencond * 1000;
        }
    }
}
