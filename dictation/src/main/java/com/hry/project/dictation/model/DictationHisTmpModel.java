package com.hry.project.dictation.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@TableName("dictation_his_tmp")
public class DictationHisTmpModel implements Serializable {

    private static final long serialVersionUID = 1L;

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
        return "DictationHisTmpModel{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", groupId=" + groupId +
        ", word=" + word +
        ", result=" + result +
        ", des=" + des +
        "}";
    }
}
