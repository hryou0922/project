package com.hry.project.dictation.speech.baidu;

/**
 * 语音返回结果
 */
public class VoiceInfoResultVo {
    /**
     * 录音文件路径：如
     *  /2021/1/你好.wav
     */
    private String voiceFilePath;

    public String getVoiceFilePath() {
        return voiceFilePath;
    }

    public void setVoiceFilePath(String voiceFilePath) {
        this.voiceFilePath = voiceFilePath;
    }
}
