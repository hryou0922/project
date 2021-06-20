package com.hry.project.dictation.dto.req.word;

import com.hry.project.dictation.dto.req.AbstractQry;

import java.util.List;

/**
 * 词语查询
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/7 15:43
 */
public class WordQry extends AbstractQry {

    private String word;

    private List<Integer> levels;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }
}
