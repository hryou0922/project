package com.hry.project.dictation.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hry
 * @since 2021-06-19
 */
@TableName("word_group_list")
public class WordGroupListModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long groupId;

    private String word;


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

    @Override
    public String toString() {
        return "WordGroupListModel{" +
        "groupId=" + groupId +
        ", word=" + word +
        "}";
    }
}
