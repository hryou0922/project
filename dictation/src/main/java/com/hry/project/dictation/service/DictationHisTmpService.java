package com.hry.project.dictation.service;

import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpBatchUpdateReq;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
public interface DictationHisTmpService extends IService<DictationHisTmpModel> {

    MyPage<DictationHisTmpModel> queryPage(DictationHisTmpQry qry);

    void batchUpdate(DictationHisTmpBatchUpdateReq req);

    /**
     * 处理临时表记录
     */
    void archive( DictationHisTmpBatchUpdateReq req);
}
