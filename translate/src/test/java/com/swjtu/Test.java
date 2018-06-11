package com.swjtu;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.*;
import com.swjtu.tts.AbstractTTS;
import com.swjtu.tts.impl.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        // TTS
        Querier<AbstractTTS> querierTTS = new Querier<>();
        // querierTTS.setParams(LANG.EN, "To be or not to be, that is a question.");
        querierTTS.setParams(LANG.ZH, "联想控股董事长、联想集团创始人柳传志、联想集团董事长兼CEO杨元庆、联想控股总裁朱立南联合发布内部信称，近期突然有人指责联想，用词相当恶毒且声音越来越大，致使联想的声誉受到了严重的挑战。为此，内部信声称要“行动起来，誓死打赢联想荣誉保卫战”。柳传志还表示，他专门和华为任正非通过电话，“任总对我表示，联想在5G标准的投票过程中的做法没有任何问题，并对联想对华为的支持表示感谢。我们一致认为，中国企业应团结，不能被外人所挑拨”。事实上，联想最近几日所遭的指责，从头到尾都像一出闹剧。哪怕华为帮忙辟谣也一点用都没有。在华为辟谣的官方微博下面，仍是满屏替华为义愤填膺，誓死打倒联想“卖国贼”的回复。.");

        querierTTS.attach(new BaiduTTS());
        querierTTS.attach(new YoudaoTTS());
        querierTTS.attach(new GoogleTTS());
        querierTTS.attach(new TencentTTS());
        querierTTS.attach(new SogouTTS());

        List<String> resultTTS = querierTTS.execute();
        for (String str : resultTTS) {
            System.out.println(str);
        }

        // translator
        Querier<AbstractTranslator> querierTrans = new Querier<>();
    //    querierTrans.setParams(LANG.EN, LANG.ZH,"Happiness is a way station between too much and too little.");
        querierTrans.setParams(LANG.ZH, LANG.EN,"Because checks in acquire are invoked before enqueuing, a newly acquiring thread may ahead of others that are blocked and queued.");
         // However, you can, if desired, define {@code tryAcquire} and/or {@code tryAcquireShared} to disable barging by internally invoking one or more of the inspection methods, thereby providing a <em>fair</em> FIFO acquisition order. In particular, most fair synchronizers can define {@code tryAcquire} to return {@code false} if {@link #hasQueuedPredecessors} (a method specifically designed to be used by fair synchronizers) returns {@code true}.  Other variations are possible.");

        querierTrans.attach(new BaiduTranslator());
        querierTrans.attach(new YoudaoTranslator());
        querierTrans.attach(new GoogleTranslator());
        querierTrans.attach(new TencentTranslator());
        querierTrans.attach(new OmiTranslator());
        querierTrans.attach(new TrycanTranslator());
        querierTrans.attach(new IcibaTranslator());
        querierTrans.attach(new SogouTranslator());

        List<String> resultTrans = querierTrans.execute();
        for (String str : resultTrans) {
            System.out.println(str);
        }
    }
}
