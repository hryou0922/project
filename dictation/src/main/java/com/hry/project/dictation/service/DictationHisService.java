package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.model.DictationHisModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
public interface DictationHisService extends IService<DictationHisModel> {

    MyPage<DictationHisModel> queryPage(DictationHisTmpQry qry);

    /**
     * 根据升序读取词语表
     * @param qry
     * @return
     */
    MyPage<DictationHisModel> queryPageOrderWordAsc(DictationHisTmpQry qry);
}
