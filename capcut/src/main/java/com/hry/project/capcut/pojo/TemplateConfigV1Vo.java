package com.hry.project.capcut.pojo;

import lombok.Data;

/**
 * 模板配置1
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:22
 */
@Data
public class TemplateConfigV1Vo extends BaseTemplateConfigVo {

    /**
     * map3长度 = 秒数时长 * 1000000L
     */
    private long mp3Duration;
    /**
     * 新的图片文件名称： pexels-pixabay-290548.jpg
     */
    private String picName;
    /**
     * 新的图片文件名称带路径：D:/douyin/sucai/tupian/pexels-pixabay-290548.jpg
     */
    private String picNameWithPath;
    /**
     * mp3 名称： 小城故事邓丽君.mp3"
     */
    private String mp3Name;
    /**
     * 作者
     *  和其他值组成 副标题：这里是作者 薛明媛-完整版； 薛明媛-简化版
     */
    private String author;



}
