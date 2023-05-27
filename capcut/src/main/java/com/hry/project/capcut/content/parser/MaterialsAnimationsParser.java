package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.MaterialsAnimationsVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 14:53
 */
public class MaterialsAnimationsParser extends BaseParser<MaterialsAnimationsVo> {
    public MaterialsAnimationsParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = MaterialsAnimationsVo.class;
    }
}
