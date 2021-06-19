package com.hry.project.dictation.msg;

import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IWordService;
import com.hry.project.dictation.speech.baidu.BaiduText2VoiceMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 自动生成语音
 */
@Component
public class Text2VoiceMsg {
    /**
     * 保存语音目录
     */
    @Value("self.voice.rootdir:D:\\db\\voice")
    private String rootDir;

    @Autowired
    private IWordService wordService;


    private BaiduText2VoiceMsg baiduText2VoiceMsg;

    @PostConstruct
    public void init(){
        baiduText2VoiceMsg = new BaiduText2VoiceMsg(rootDir);
    }

    public void exeucte(){
        List<WordModel> modelList = wordService.list();
        for(WordModel model : modelList){

        }
    }
}
