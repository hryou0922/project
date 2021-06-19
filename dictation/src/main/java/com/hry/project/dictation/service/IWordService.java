package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordQry;
import com.hry.project.dictation.model.WordModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
public interface IWordService extends IService<WordModel> {

    WordModel selectByWord(String word);

    MyPage<WordModel> queryPage(WordQry qry);
}
