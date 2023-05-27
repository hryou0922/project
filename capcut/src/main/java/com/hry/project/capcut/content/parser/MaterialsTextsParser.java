package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.MaterialsTextsVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 23:27
 */
public class MaterialsTextsParser extends BaseParser<MaterialsTextsVo> {
    public MaterialsTextsParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = MaterialsTextsVo.class;
    }
}
