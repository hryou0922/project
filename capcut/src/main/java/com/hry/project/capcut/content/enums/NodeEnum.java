package com.hry.project.capcut.content.enums;

import com.hry.project.capcut.content.parser.*;
import com.hry.project.capcut.content.vo.*;
import lombok.Getter;


public enum NodeEnum {
    /**
     *
     */
    SIMPLE_ROOT("simple-root",SimpleRootParser.class, SimpleRootVo.class, ""),
    TRACKS("tracks",TracksParser.class, TracksVo.class, ""),
    MATERIALS_VIDEOS("materials:videos", MaterialsVideosParser.class, MaterialsVideosVo.class, ""),
    MATERIALS_VIDEOEFFECTS("materials:video_effects", MaterialsVideoEffectsParser.class, MaterialsVideoEffectsVo.class, ""),
    MATERIALS_TEXTS("materials:texts", MaterialsTextsParser.class, MaterialsTextsVo.class, ""),
    MATERIALS_AUDIOS("materials:audios", MaterialsAudiosParser.class, MaterialsAudiosVo.class, ""),
    MATERIALS_MATERIAL_ANIMATIONS("materials:material_animations", MaterialsAnimationsParser.class, MaterialsAnimationsVo.class, ""),
    KEYFRAMES_VIDEOS("keyframes:videos", KeyframesVideosParser.class, KeyframesVideosVo.class, ""),

//    MATERIALS_AUDIOS("materials:material_animations", MaterialsAudiosParser.class, MaterialsAudiosVo.class, ""),
    ;
    @Getter
    private String name;
    @Getter
    private Class parserClass;
    @Getter
    private Class baseElementVoClass;
    @Getter
    private String desc;

    <T extends BaseParser, V extends BaseElementVo> NodeEnum(String name, Class<T> parserClass, Class<V> baseElementVoClass, String desc) {
        this.name = name;
        this.parserClass = parserClass;
        this.baseElementVoClass = baseElementVoClass;
        this.desc = desc;
    }

    //    public static EventTypeEnum valueOfType(String type){
//        if(type == null){
//            return null;
//        }
//
//        EventTypeEnum enumType = null;
//        for(EventTypeEnum tmpEnumType : EventTypeEnum.values()){
//            if(tmpEnumType.id.equalsIgnoreCase(type)){
//                enumType = tmpEnumType;
//                break;
//            }
//        }
//        return enumType;
//    }
}
