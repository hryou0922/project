package com.hry.project.dictation.service;

import com.hry.project.dictation.model.WordModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
public interface WordService extends IService<WordModel> {

    WordModel selectByWord(String word);
}
