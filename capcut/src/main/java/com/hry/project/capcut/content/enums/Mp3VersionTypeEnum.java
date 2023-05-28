package com.hry.project.capcut.content.enums;

import lombok.Getter;


/**
 * mp3 版本类型
 */
public enum Mp3VersionTypeEnum {
    /**
     * TODO 这里后续添加
     */
    FULL("full","完整版", ""),
    ;
    @Getter
    private String type;
    @Getter
    private String name;
    @Getter
    private String desc;


    Mp3VersionTypeEnum(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }
}
