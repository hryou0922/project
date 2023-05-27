package com.hry.project.capcut.content.template;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hry.project.capcut.content.DraftContent;
import com.hry.project.capcut.pojo.BaseTemplateConfigVo;
import com.hry.project.capcut.utils.GsonBox;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:29
 */
@Slf4j
public abstract class BaseTemplateProcessMsg<T extends BaseTemplateConfigVo> implements TemplateProcessMsg<T> {

    @Override
    public String execute(String content, T baseTemplateConfigVo) {
        log.info("模板配置替换内容:{}", GsonBox.PUBLIC.toJson(baseTemplateConfigVo));
        JsonObject root = JsonParser.parseString(content).getAsJsonObject();
        DraftContent draftContent = new DraftContent(root);
        return GsonBox.PUBLIC.toJson(execute0(draftContent, baseTemplateConfigVo));
    }

    protected abstract JsonObject execute0(DraftContent draftContent, T baseTemplateConfigVo);

}
