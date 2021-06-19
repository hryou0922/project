package com.hry.project.dictation.datamigration;

import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.enums.FamiliarLevelEnum;
import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IDictationHisService;
import com.hry.project.dictation.service.IWordGroupService;
import com.hry.project.dictation.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * 解析类
 *  正序读取历史表，并逐步更新词语的熟练度信息
 *
 *
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 14:19
 */
@Component
public class DictationHis2WordMigrationMsg {
    @Autowired
    private IDictationHisService dictationHisService;
    @Autowired
    private IWordService wordService;
    @Autowired
    private IWordGroupService wordGroupService;


    /**
     *  正序读取历史表，并逐步更新词语的熟练度信息
     */
    public void dictationHist2Word(){
        DictationHisTmpQry qry = new DictationHisTmpQry();
        qry.setPageSize(Integer.MAX_VALUE);
        MyPage<DictationHisModel> myPage = dictationHisService.queryPageOrderWordAsc(qry);
        for(DictationHisModel tmpModel : myPage.getItems()){
            Integer tmpResult = tmpModel.getResult();
            long dictationHisModelCreateTime = tmpModel.getCreateTime().getTime();
            String word = tmpModel.getWord();
            long id = tmpModel.getId();
            // 本次听写是成功还是失败
            boolean isDitationSucess = (tmpResult != null
                    && tmpResult == DictationHisModel.DICTATION_RESULT_SUCCESS) ? true : false;

            // 更新词语表状态
            WordModel dbModel = wordService.selectByWord(word);
            if(dbModel != null){
                Date oldDate = dbModel.getLevelTime();
                // 是否已经被处理：根据时间进行判断
                if(oldDate == null || oldDate.getTime() < dictationHisModelCreateTime ){
                    // 数据未被处理，则进行处理，词语听写总数和最后状态进行更新
                    Integer total = dbModel.getTotal();
                    total = ((total == null) ? 1 : ++total);
                    dbModel.setTotal(total);
                    Integer newNextLevel = FamiliarLevelEnum.getNextLevel(dbModel.getLevel(), isDitationSucess).getLevel();
                    dbModel.setLevel(newNextLevel);
                    dbModel.setLastResult(tmpResult);
                    dbModel.setLevelTime(tmpModel.getCreateTime());
                    // 更新状态
                    wordService.updateById(dbModel);
                }
            }
        }
    }

    /**
     * 更新组信息
     */
    public void updateWordGroupStaticInfo(){
        ArrayList<Long> groudIdList = new ArrayList<>();
       for(WordGroupModel model : wordGroupService.list()){
           groudIdList.add(model.getId());
        }

        wordGroupService.updateGroupInfo(groudIdList.toArray(new Long[0]));
    }
}
