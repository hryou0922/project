package com.hry.project.capcut.content.parser;

import com.google.gson.JsonObject;
import com.hry.project.capcut.content.vo.MaterialsVideoEffectsVo;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/26 22:42
 */
public class MaterialsVideoEffectsParser extends BaseParser<MaterialsVideoEffectsVo> {
    public MaterialsVideoEffectsParser(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.baseContentVo = MaterialsVideoEffectsVo.class;
    }


}
