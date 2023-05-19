package com.hry.project.capcut.pojo;

import lombok.Data;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:10
 */
@Data
public class FileMp3InfoVo {
    /**
     * 语音时长
     */
    private long durationSecond;
    /**
     * 文件原始名称
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
