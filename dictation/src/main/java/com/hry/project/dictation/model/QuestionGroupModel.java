package com.hry.project.dictation.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hry
 * @since 2021-07-05
 */
@TableName("question_group")
public class QuestionGroupModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Date createTime;

    private String name;

    private Integer type;

    private Integer total;

    private Integer passRate;

    private Integer goodRate;

    private Integer excellentRate;

    private Date resultTime;

    private Integer status;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "QuestionGroupModel{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", name=" + name +
        ", type=" + type +
        ", total=" + total +
        ", passRate=" + passRate +
        ", goodRate=" + goodRate +
        ", excellentRate=" + excellentRate +
        ", resultTime=" + resultTime +
        ", status=" + status +
        ", des=" + des +
        "}";
    }
}
