package com.hry.project.dictation.msg;

import com.hry.project.dictation.speech.baidu.VoiceInfoResultVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/7/6 11:46
 */
public interface IVoicePlayMsg {

    /**
     * 语音播放
     * @param text 播放文本
     * @param voiceFile 文本对应的录音文件可为空
     * @param relativeDir 录音文件存储的相对位置，可为空
     * @return 如果生成录音文件，则返回非空，且录音文件路径非空
     */
    VoiceInfoResultVo playOneWord(String text, String voiceFile, String relativeDir);

    default VoiceInfoResultVo playOneWord(String text, String voiceFile){
        return playOneWord(text, voiceFile, "/");
    }
}
