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
 * @since 2021-06-19
 */
@TableName("word_group")
public class WordGroupModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Date createTime;

    private String name;

    private Integer type;

    private Integer wordTotal;

    private Integer passRate;

    private Integer goodRate;

    private Integer excellentRate;

    private Date resultTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWordTotal() {
        return wordTotal;
    }

    public void setWordTotal(Integer wordTotal) {
        this.wordTotal = wordTotal;
    }

    public Integer getPassRate() {
        return passRate;
    }

    public void setPassRate(Integer passRate) {
        this.passRate = passRate;
    }

    public Integer getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(Integer goodRate) {
        this.goodRate = goodRate;
    }

    public Integer getExcellentRate() {
        return excellentRate;
    }

    public void setExcellentRate(Integer excellentRate) {
        this.excellentRate = excellentRate;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "WordGroupModel{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", name=" + name +
        ", type=" + type +
        ", wordTotal=" + wordTotal +
        ", passRate=" + passRate +
        ", goodRate=" + goodRate +
        ", excellentRate=" + excellentRate +
        ", resultTime=" + resultTime +
        ", des=" + des +
        "}";
    }
}
