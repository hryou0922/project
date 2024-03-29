package com.hry.project.capcut.pojo;

import com.hry.project.capcut.content.enums.LyricTruncatedModeEnum;
import com.hry.project.capcut.content.enums.Mp3VersionTypeEnum;
import lombok.Data;

/**
 * 模板配置1
 *
 *  扩展字段：
 *      效果：下雨、其他效果等
 *
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/27 21:22
 */
@Data
public class TemplateConfigV1Vo extends BaseTemplateConfigVo {

    /**
     * 总视频长度 = 秒数时长 * 1000000L
     */
    private long duration;
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
     * 新的mp3,带路径： D:\douyin\sucai\mp3/小城故事#邓丽君.mp3
     */
    private String mp3NameWithPath;
    /**
     * mp3 类型
     */
    private Mp3VersionTypeEnum mp3VersionTypeEnum;
    /**
     * 作者
     *  和其他值组成 副标题：这里是作者 薛明媛-完整版； 薛明媛-简化版
     */
    private String author;
    /**
     * 是否已经有歌词：如果有歌词，则执行歌词处理
     */
    private boolean lyric;

    /**
     * 歌词位置
     */
    private double lyricTransformX;
    private double lyricTransformY;
    /**
     * 歌词文字大小
     */
    private int lyricFontSize;
    /**
     * 首个歌词间隔值 = 秒数时长 * 1000000L
     */
    private long lyricGapTime;

    /**
     * 后一个歌词超过前1个歌词这个值则丢弃 = 秒数时长 * 1000000L
     */
    private long lyricDiscardThreshold;

    /**
     * 歌词截断起始值，主要给mp3做截断使用
     *  回传可被修改
     */
    private long alllLricStart;
    /**
     * 歌词截断的结束值
     *  回传可被修改，默认值为空
     */
    private Long alllLricEnd;
    /**
     * 截断的模式
     */
    private LyricTruncatedModeEnum lyricTruncatedModeEnum;


    public TemplateConfigV1Vo(){
        lyricTransformX = 0;
        lyricTransformY = -0.2593902439024389;
        lyricFontSize = 12;
        lyricGapTime = (long) (1.5 * 1000000L);
        // 可考虑传参数
        lyricDiscardThreshold = 20 * 1000000L;
        alllLricStart = 0;
        this.mp3VersionTypeEnum = Mp3VersionTypeEnum.FULL;
        // 可考虑传参数
        this.lyricTruncatedModeEnum = LyricTruncatedModeEnum.BEGIN_TRUNCATE;
    }

}
