package com.hry.project.capcut.pojo;

import lombok.Data;

/**
 * 模板处理后的返回内容
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 22:29
 */
@Data
public class TemplateReturnInfoV1Vo {
    /**
     * 语音时长
     */
    private long durationSecond;
    /**
     * 歌名称：不带文件后缀
     */
    private String fileName;
    /**
     * 作者
     */
    private String author;
    /**
     * 标题
     */
    private String title;
}
