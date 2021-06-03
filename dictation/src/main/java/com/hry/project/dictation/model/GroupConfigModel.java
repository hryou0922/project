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
 * @since 2021-06-03
 */
@TableName("group_config")
public class GroupConfigModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer type;

    private Date createTime;

    private Date lastRunTime;

    private Date nextRunTime;

    private Date deadTime;

    private Integer nextInterval;

    private String runTimeRange;

    private Integer enable;

    private String des;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public Date getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(Date nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Integer getNextInterval() {
        return nextInterval;
    }

    public void setNextInterval(Integer nextInterval) {
        this.nextInterval = nextInterval;
    }

    public String getRunTimeRange() {
        return runTimeRange;
    }

    public void setRunTimeRange(String runTimeRange) {
        this.runTimeRange = runTimeRange;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "GroupConfigModel{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", createTime=" + createTime +
        ", lastRunTime=" + lastRunTime +
        ", nextRunTime=" + nextRunTime +
        ", deadTime=" + deadTime +
        ", nextInterval=" + nextInterval +
        ", runTimeRange=" + runTimeRange +
        ", enable=" + enable +
        ", des=" + des +
        "}";
    }
}
