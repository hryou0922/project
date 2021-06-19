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
@TableName("word")
public class WordModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String word;

    private Integer type;

    private String voiceFile;

    private Integer lastResult;

    private Integer level;

    private Date levelTime;

    private Integer total;

    private String des;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
        this.voiceFile = voiceFile;
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
        return "WordModel{" +
        "id=" + id +
        ", word=" + word +
        ", type=" + type +
        ", voiceFile=" + voiceFile +
        ", lastResult=" + lastResult +
        ", level=" + level +
        ", levelTime=" + levelTime +
        ", total=" + total +
        ", des=" + des +
        "}";
    }
}
