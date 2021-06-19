package com.hry.project.dictation.msg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IWordService;
import com.hry.project.dictation.speech.baidu.BaiduText2VoiceMsg;
import com.hry.project.dictation.speech.baidu.VoiceInfoResultVo;
import com.hry.project.dictation.utils.VoiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * 语音播放
 */
@Component
public class VoicePlayMsg {
    private static final Logger logger = LoggerFactory.getLogger(VoicePlayMsg.class);

    @Value("${self.voice.rootdir:D:\\db\\voice}")
    private String rootDir;
    @Value("${self.voice.playnum: 4}")
    private int playNum;
    @Value("${self.voice.sleep: 5}")
    private int sleepSencond;

    @Autowired
    private IWordService wordService;

    private BaiduText2VoiceMsg baiduText2VoiceMsg;

    @PostConstruct
    public void init(){
        baiduText2VoiceMsg = new BaiduText2VoiceMsg(rootDir);
    }


    public void execute(int grade, int unit, String article ){
        //
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("grade", grade);
        queryWrapper.eq("unit", unit);
        queryWrapper.eq("article", article);

        List<WordModel> wordModelList = wordService.list(queryWrapper);
        for(WordModel wordModel : wordModelList){
            playOneWord(wordModel);
        }

    }

    public void playOneWord(WordModel wordModel){
        String word = wordModel.getWord();
        if(!StringUtils.hasLength(word)){
            return;
        }
        String voiceFile = wordModel.getVoiceFile();
        if(!StringUtils.hasText(voiceFile)){
            text2Voice(wordModel);
            // 重新获取新的录音文件地址
            voiceFile = wordService.selectByWord(word).getVoiceFile();
        }
        if(StringUtils.hasText(voiceFile)){
            String file = rootDir + File.separator + voiceFile;
            logger.info("词语=[{}]，路径=[{}]", word, file);
            int t = playNum;
            while(t-- > 0 ) {
                VoiceUtils.play(file);
            }
        }

        try {
            Thread.sleep(word.length() * sleepSencond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文本转换为语音文件
     * @param model
     * @return
     */
    private void text2Voice(WordModel model){
//        int grade = model.getGrade();
//        int unit = model.getUnit();
//        String article = model.getArticle();
        String word = model.getWord();

      //  String relativeDir = "/" + grade + "/" + unit + "/" + article + "/";
        String relativeDir = "/";
        VoiceInfoResultVo voiceInfoResultVo = baiduText2VoiceMsg.text2wav(word, relativeDir);
        if(voiceInfoResultVo != null){
            // 语音创建成功
            WordModel updateModel = new WordModel();
            updateModel.setId(model.getId());
            updateModel.setVoiceFile(voiceInfoResultVo.getVoiceFilePath());
            wordService.updateById(updateModel);
        }
    }
}
