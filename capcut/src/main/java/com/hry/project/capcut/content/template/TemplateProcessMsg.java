package com.hry.project.capcut.content.template;

import com.hry.project.capcut.pojo.BaseTemplateConfigVo;

/**
 * 模板类
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:29
 */
public interface TemplateProcessMsg<T extends BaseTemplateConfigVo> {
    /**
     * 根据参数配置模板
     * @param content 原始内容
     * @param baseTemplateConfigVo 计划处理的内容
     * @return 解析后的内容
     */
    String execute(String content, T baseTemplateConfigVo);
}
