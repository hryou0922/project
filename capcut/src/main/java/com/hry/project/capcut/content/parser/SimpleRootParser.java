package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.SimpleRootVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:00
 */
public class SimpleRootParser extends BaseParser<SimpleRootVo> {

    public SimpleRootParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = SimpleRootVo.class;
    }

}
