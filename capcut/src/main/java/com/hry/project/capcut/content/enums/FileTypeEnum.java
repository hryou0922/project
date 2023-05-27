package com.hry.project.capcut.content.enums;

import lombok.Getter;


public enum FileTypeEnum {
    /**
     *
     */
    VIDEO("video","video", "视频"),
    EFFECT("effect","effect", "效果"),
    AUDIO("audio", "audio","音频")
    ;
    @Getter
    private String type;
    @Getter
    private String name;
    @Getter
    private String desc;


    FileTypeEnum(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }
}
