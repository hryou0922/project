package com.hry.project.capcut.content.enums;

import lombok.Getter;

/**
 * 歌词的截断模式
 */
public enum LyricTruncatedModeEnum {
    /**
     *
     */
    FULL("full","full", "完整版，不截断"),
    BEGIN_TRUNCATE("begin_truncate","begin_truncate", "首字母截断"),
    BEGIN_TRUNCATE_FIRST_PART("begin_truncate_first_part", "begin_truncate_first_part","首字母阶段，只保留第一阶段")
    ;
    @Getter
    private String type;
    @Getter
    private String name;
    @Getter
    private String desc;


    LyricTruncatedModeEnum(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }
}
