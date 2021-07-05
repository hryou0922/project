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
@TableName("question")
public class QuestionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private Integer type;

    private String topic;

    private Integer voiceNeed;

    private String voiceFile;

    private String content;

    private String answer;

    private Integer lastResult;

    private Integer level;

    private Date levelTime;

    private Integer total;

    private String des;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getVoiceNeed() {
        return voiceNeed;
    }

    public void setVoiceNeed(Integer voiceNeed) {
        this.voiceNeed = voiceNeed;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getLastResult() {
        return lastResult;
    }

    public void setLastResult(Integer lastResult) {
        this.lastResult = lastResult;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getLevelTime() {
        return levelTime;
    }

    public void setLevelTime(Date levelTime) {
        this.levelTime = levelTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
        "id=" + id +
        ", type=" + type +
        ", topic=" + topic +
        ", voiceNeed=" + voiceNeed +
        ", voiceFile=" + voiceFile +
        ", content=" + content +
        ", answer=" + answer +
        ", lastResult=" + lastResult +
        ", level=" + level +
        ", levelTime=" + levelTime +
        ", total=" + total +
        ", des=" + des +
        "}";
    }
}
