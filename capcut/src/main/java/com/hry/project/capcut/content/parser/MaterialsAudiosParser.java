package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.MaterialsAudiosVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 23:35
 */
public class MaterialsAudiosParser extends BaseParser<MaterialsAudiosVo> {

    public MaterialsAudiosParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = MaterialsAudiosVo.class;
    }
}
