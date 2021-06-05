package com.hry.project.dictation.speech.baidu;

import com.hry.project.dictation.utils.CommonJsonUtils;
import org.junit.Test;

public class BaiduText2VoiceMsgTest {

    @Test
    public void a (){
        String dir = "D:\\tmp\\tmp2";
        BaiduText2VoiceMsg baiduText2VoiceMsg = new BaiduText2VoiceMsg(dir);
        VoiceInfoResultVo voiceInfoResultVo = baiduText2VoiceMsg.text2wav("飞机飞来了","2012");
        System.out.println(CommonJsonUtils.toJsonString(voiceInfoResultVo));
        VoiceInfoResultVo voiceInfoResultVo12 =  baiduText2VoiceMsg.text2wav("你好", "");
        System.out.println(CommonJsonUtils.toJsonString(voiceInfoResultVo12));
    }
}
