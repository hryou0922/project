package com.hry.project.dictation.enums;

/**
 * 组类型
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/11 10:55
 */
public enum WordGroupEnum {
    /**
     * 组类型
     * 1：默认；2：临时组
     */
    DEFAULT(1, "默认组"),
    TMP_GROUP(2, "临时组")
    ;
    private int type;
    private String msg;

    WordGroupEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static WordGroupEnum valueOfType(int type){
        WordGroupEnum enumRtn = null;
        for(WordGroupEnum enableEnum : WordGroupEnum.values()){
            if(enableEnum.getType() == type){
                enumRtn = enableEnum;
                break;
            }
        }
        return enumRtn;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
