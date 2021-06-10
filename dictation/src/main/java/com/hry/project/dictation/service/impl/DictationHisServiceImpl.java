package com.hry.project.dictation.service.impl;

import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.mapper.DictationHisMapper;
import com.hry.project.dictation.service.DictationHisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.utils.CheckUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@Service
public class DictationHisServiceImpl extends ServiceImpl<DictationHisMapper, DictationHisModel> implements DictationHisService {

    @Override
    public void updateDictationResult(String word, int result){
        CheckUtil.checkNotEmpty("word", word);

        // 更新word的最后听写结果

        //
    }
}
