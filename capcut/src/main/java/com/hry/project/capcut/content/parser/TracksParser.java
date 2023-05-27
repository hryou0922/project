package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.TracksVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 14:45
 */
public class TracksParser extends BaseParser<TracksVo> {
    public TracksParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = TracksVo.class;
    }
}
