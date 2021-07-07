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

    private BaiduText2VoiceMsg baiduText2VoiceMsg;

    public VoicePlayMsgImpl(String rootDir, int playNum, int sleepSencond) {
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
            Thread.sleep(text.length() * sleepSencond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return voiceInfoResultVo;
    }
}
