package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.MaterialsVideosVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 22:32
 */
public class MaterialsVideosParser extends BaseParser<MaterialsVideosVo> {

    public MaterialsVideosParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = MaterialsVideosVo.class;
    }



}
