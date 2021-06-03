package com.hry.project.dictation.speech.baidu;

import org.junit.Test;

public class BaiduText2VoiceMsgTest {

    @Test
    public void a (){
        BaiduText2VoiceMsg baiduText2VoiceMsg = new BaiduText2VoiceMsg();
        baiduText2VoiceMsg.text2wav("啊啊啊");
    }
}
