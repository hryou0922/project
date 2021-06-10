package com.hry.project.dictation.dto.req.word;

import com.hry.project.dictation.dto.req.AbstractQry;

public class DictationHisTmpQry extends AbstractQry {
    private String word;

    private Integer result;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
