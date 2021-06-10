package com.hry.project.dictation.service;

import com.hry.project.dictation.model.DictationHisModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
public interface DictationHisService extends IService<DictationHisModel> {

    /**
     * 更新听写结果
     * @param word
     * @param result
     */
    void updateDictationResult(String word, int result);
}
