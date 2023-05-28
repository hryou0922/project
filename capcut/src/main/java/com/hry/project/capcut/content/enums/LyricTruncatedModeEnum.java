package com.hry.project.capcut.content.enums;

import lombok.Getter;

/**
 * 歌词的截断模式
 */
public enum LyricTruncatedModeEnum {
    /**
     *
     */
    FULL("1","full", "完整版，不截断"),
    BEGIN_TRUNCATE("2","begin_truncate", "首字母截断"),
    BEGIN_TRUNCATE_ONLY_FIRST_PART("3", "begin_truncate_only_first_part","首字母阶段，只保留第一阶段")
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

    public static LyricTruncatedModeEnum valueOfType(String type){
        if(type == null){
            return BEGIN_TRUNCATE;
        }

        LyricTruncatedModeEnum enumType = null;
        for(LyricTruncatedModeEnum tmpEnumType : LyricTruncatedModeEnum.values()){
            if(tmpEnumType.type.equalsIgnoreCase(type)){
                enumType = tmpEnumType;
                break;
            }
        }
        return enumType;
    }

}
