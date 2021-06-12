package com.hry.project.dictation.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hry
 * @since 2021-06-10
 */
@TableName("dictation_his")
public class DictationHisModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 听写的结果，成功或失败
     */
    public static final int DICTATION_RESULT_SUCCESS = 1;
    public static final int DICTATION_RESULT_FAIL = 0;
    // 直接删除
    public static final int DICTATION_RESULT_DELETE = 99;


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Date createTime;

    private Long groupId;

    private String word;

    private Integer result;

    private String des;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "DictationHisModel{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", groupId=" + groupId +
        ", word=" + word +
        ", result=" + result +
        ", des=" + des +
        "}";
    }
}
