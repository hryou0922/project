package com.hry.project.dictation.msg;

import com.hry.project.dictation.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VoicePlayMsgTest extends BaseTest {
    @Autowired
    private VoicePlayMsg voicePlayMsg;

    @Test
    public void a(){
    //    voicePlayMsg.execute(2,6, "古诗二首");
        voicePlayMsg.execute(2,6, "雷雨");
    }
}
