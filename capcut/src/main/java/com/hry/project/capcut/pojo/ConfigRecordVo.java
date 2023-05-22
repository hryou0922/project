package com.hry.project.capcut.pojo;

import lombok.Data;

/**
 * mp3名称,图片名称
 * FLOW#方大同王力宏.mp3,pexels-pixabay-46164.jpg
 *
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/19 21:53
 */
@Data
public class ConfigRecordVo {
    /**
     * mp3的文件名称，带后缀
     */
    private String mp3Name;
    /**
     * 图片名称，带后缀
     */
    private String picName;

}
