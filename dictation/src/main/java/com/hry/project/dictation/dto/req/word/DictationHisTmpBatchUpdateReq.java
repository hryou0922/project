package com.hry.project.dictation.dto.req.word;

import java.util.List;

/**
 * 批量修改记录
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/10 17:33
 */
public class DictationHisTmpBatchUpdateReq {
    private List<Long> ids;

    /**
     * @see com.hry.project.dictation.model.DictationHisModel
     */
    private int result;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
