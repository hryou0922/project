package com.hry.project.dictation.enums;

/**
 * 熟练度等级
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/11 10:55
 */
public enum  FamiliarLevelEnum {
    /**
     * 等级说明
     */
    EXTREMELY_PRACTICE(-3, "极度需要练习"),
    NEED_PRACTICE(-2, "需要练习"),
    NO_PASS(-1, "不及格"),
    UN_KNOW(0, "待测试"),
    PASS(1, "及格"),
    GOOD(2, "良"),
    EXCELLENT(3, "优秀")
    ;
    private int level;
    private String msg;

    FamiliarLevelEnum(int level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    /**
     * 获取下一个级别
     * @param oldLevel 老的级别，可能为空
     * @param isSuccess 新的结果是成功，则传入true，否则传入false
     * @return 返回值不能为空
     */
    public static FamiliarLevelEnum getNextLevel(Integer oldLevel, boolean isSuccess){

        // 先找到异常情况先处理
        FamiliarLevelEnum oldEnum;
        if(oldLevel == null){
            oldEnum = UN_KNOW;
        }else {
            oldEnum = valueOfType(oldLevel);
            if(oldEnum == null){
                oldEnum = UN_KNOW;
            }
        }

        FamiliarLevelEnum rtn;
        if(isSuccess){
            // 本次的处理结果为成功
            switch (oldEnum){
                case EXTREMELY_PRACTICE: rtn = NEED_PRACTICE; break;
                case NEED_PRACTICE: rtn = NO_PASS; break;
                case NO_PASS:
                case UN_KNOW:
                    rtn = PASS;
                    break;
                case PASS: rtn = GOOD; break;
                case GOOD:
                case EXCELLENT:
                    rtn = EXCELLENT;
                    break;
                default:
                    rtn = PASS;
            }
        }else {
            // 本次的处理结果为失败
            switch (oldEnum){
                case EXTREMELY_PRACTICE:
                case NEED_PRACTICE:
                    rtn = EXTREMELY_PRACTICE;
                    break;
                case NO_PASS: rtn = NEED_PRACTICE; break;
                case UN_KNOW:
                case PASS:
                    rtn = NO_PASS;
                    break;
                case GOOD: rtn = PASS; break;
                case EXCELLENT: rtn  = GOOD; break;
                default:
                    rtn = NO_PASS;
            }
        }
        return rtn;
    }

    public static FamiliarLevelEnum valueOfType(int level){
        FamiliarLevelEnum enumRtn = null;
        for(FamiliarLevelEnum enableEnum : FamiliarLevelEnum.values()){
            if(enableEnum.getLevel() == level){
                enumRtn = enableEnum;
                break;
            }
        }
        return enumRtn;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
