package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.KeyframesVideosVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 20:46
 */
public class KeyframesVideosParser extends BaseParser<KeyframesVideosVo> {

    public KeyframesVideosParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = KeyframesVideosVo.class;
    }
}
