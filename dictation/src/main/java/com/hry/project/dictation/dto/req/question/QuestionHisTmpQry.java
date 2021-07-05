package com.hry.project.dictation.dto.req.question;

import com.hry.project.dictation.dto.req.AbstractQry;

public class QuestionHisTmpQry extends AbstractQry {
    private String topic;

    private Integer result;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
