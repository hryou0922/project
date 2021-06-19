package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpBatchUpdateReq;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.enums.FamiliarLevelEnum;
import com.hry.project.dictation.mapper.DictationHisMapper;
import com.hry.project.dictation.mapper.DictationHisTmpMapper;
import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IDictationHisTmpService;
import com.hry.project.dictation.service.IWordGroupService;
import com.hry.project.dictation.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-08
 */
@Service
public class DictationHisTmpServiceImpl extends ServiceImpl<DictationHisTmpMapper, DictationHisTmpModel> implements IDictationHisTmpService {
    @Autowired
    private DictationHisTmpMapper dictationHisTmpMapper;
    @Autowired
    private DictationHisMapper dictationHisMapper;
    @Autowired
    private IWordService wordService;
    @Autowired
    private IWordGroupService wordGroupService;

    @Override
    public MyPage<DictationHisTmpModel> queryPage(DictationHisTmpQry qry) {
        QueryWrapper<DictationHisTmpModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(qry.getWord()) , "word", qry.getWord());
        queryWrapper.eq(qry.getResult() != null, "result", qry.getResult());
        queryWrapper.orderByDesc("create_time");

        Page<DictationHisTmpModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<DictationHisTmpModel> page = page(pageQry, queryWrapper);

        MyPage<DictationHisTmpModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public void batchUpdate(DictationHisTmpBatchUpdateReq req){
        int result = req.getResult();
        List<Long> idList = req.getIds();
        if(idList != null){
            if(result == DictationHisModel.DICTATION_RESULT_DELETE){
                // 执行批量删除
                baseMapper.deleteBatchIds(idList);
            }else {
                // 更新正确、错误
                for (Long id : idList) {
                    if (id != null) {
                        DictationHisTmpModel tmp = new DictationHisTmpModel();
                        tmp.setId(id);
                        tmp.setResult(result);
                        baseMapper.updateById(tmp);
                    }
                }
            }
        }
    }

    @Override
    public void archive( DictationHisTmpBatchUpdateReq req) {
        List<DictationHisTmpModel> list = this.listByIds(req.getIds());
        // 保存
        Set<Long> groupIdSet = new HashSet<>();
        for(DictationHisTmpModel tmpModel : list){
            Integer tmpResult = tmpModel.getResult();
            long dictationHisModelCreateTime = tmpModel.getCreateTime().getTime();
            String word = tmpModel.getWord();
            Long groupId = tmpModel.getGroupId();
            if(groupId != null){
                groupIdSet.add(groupId);
            }
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

            // 写入正式表
            if(dictationHisMapper.selectById(id) == null) {
                // 写入正式表
                DictationHisModel hisModel = new DictationHisModel();
                hisModel.setId(tmpModel.getId());
                hisModel.setCreateTime(tmpModel.getCreateTime());
                hisModel.setGroupId(tmpModel.getGroupId());
                hisModel.setWord(tmpModel.getWord());
                hisModel.setResult(tmpModel.getResult());
                hisModel.setDes(tmpModel.getDes());
                dictationHisMapper.insert(hisModel);
            }

            // TODO 删除已经处理的记录
            dictationHisTmpMapper.deleteById(tmpModel.getId());
        }

        // TODO 更新组表统计信息
        wordGroupService.updateGroupInfo(groupIdSet.toArray(new Long[0]));
    }

}
