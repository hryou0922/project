package com.hry.project.dictation.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hry
 * @since 2021-07-05
 */
@TableName("question_group_list")
public class QuestionGroupListModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long groupId;

    private String questionId;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuestionGroupListModel{" +
        "groupId=" + groupId +
        ", questionId=" + questionId +
        "}";
    }
}
