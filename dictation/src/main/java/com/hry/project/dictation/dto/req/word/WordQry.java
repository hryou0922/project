package com.hry.project.dictation.dto.req.word;

import com.hry.project.dictation.dto.req.AbstractQry;

/**
 * 词语查询
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/7 15:43
 */
public class WordQry extends AbstractQry {
    private Integer grade;

    private Integer unit;

    private String article;

    private String word;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
