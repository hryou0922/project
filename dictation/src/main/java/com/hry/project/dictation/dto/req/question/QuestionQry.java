package com.hry.project.dictation.dto.req.question;

import com.hry.project.dictation.dto.req.AbstractQry;

import java.util.List;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/29 19:38
 */
public class QuestionQry  extends AbstractQry {
    private String topic;

    private List<Integer> levels;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }
}
