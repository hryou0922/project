package com.hry.project.dictation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordGroupQry;
import com.hry.project.dictation.enums.FamiliarLevelEnum;
import com.hry.project.dictation.mapper.WordGroupListMapper;
import com.hry.project.dictation.mapper.WordGroupMapper;
import com.hry.project.dictation.model.WordGroupListModel;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.IWordGroupService;
import com.hry.project.dictation.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hry
 * @since 2021-06-15
 */
@Service
public class WordGroupServiceImpl extends ServiceImpl<WordGroupMapper, WordGroupModel> implements IWordGroupService {
    @Autowired
    private WordGroupListMapper wordGroupListMapper;

    @Override
    public WordGroupModel selectWrodGroupByGroupName(String name) {
        if(!StringUtils.hasText(name)){
            return null;
        }
        QueryWrapper<WordGroupModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MyPage<WordGroupModel> queryWordGroupPage(WordGroupQry qry) {
        if(qry == null){
            qry = new WordGroupQry();
        }
        QueryWrapper<WordGroupModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(qry.getName()) , "name", qry.getName());
        queryWrapper.orderByDesc("create_time");

        Page<WordGroupModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());


        Page<WordGroupModel> page = page(pageQry, queryWrapper);

        MyPage<WordGroupModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public MyPage<WordModel> queryWordGroupListPage(WordGroupQry qry) {
        if(qry == null){
            qry = new WordGroupQry();
        }

        QueryWrapper<WordModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(qry.getGroupId() != null, "group_id", qry.getGroupId());

        Page<WordModel> pageQry = new Page<>();
        pageQry.setSize(qry.getPageSize().longValue());
        pageQry.setCurrent(qry.getPageNum());

        Page<WordModel> page = wordGroupListMapper.queryWordGroupListPage(pageQry, queryWrapper);
        MyPage<WordModel> myPage = MyPage.create(page.getRecords());
        myPage.setTotal((int) page.getTotal());
        return myPage;
    }

    @Override
    public boolean isWordInGroup(long groupId, String word) {
        CheckUtil.checkNotEmpty("word", word);

        QueryWrapper<WordGroupListModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true, "group_id", groupId);
        queryWrapper.eq(true, "word", word);

        if(wordGroupListMapper.selectList(queryWrapper).size() == 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void save(WordGroupModel entity, List<WordGroupListModel> listModelList) {
        CheckUtil.checkNotEmpty("name", entity.getName());
        if(listModelList == null || listModelList.size() == 0){
            throw new RuntimeException("每个组里的词语列表不能为空");
        }
        // 组数量
        int wordTotal = listModelList.size();
        entity.setWordTotal(wordTotal);
        entity.setCreateTime(new Date());
        // 添加记录
        baseMapper.insert(entity);

        for(WordGroupListModel tmpModel : listModelList){
            // 补全组id
            tmpModel.setGroupId(entity.getId());
            // 添加数据
            wordGroupListMapper.insert(tmpModel);
        }
    }

    @Override
    public void updateGroupInfo(Long[] groupIdArray){
        if(groupIdArray != null){
            for(Long groupId : groupIdArray){
                if(groupId != null){
                    // 获取groupId 所有的记录，并进行统计
                    WordGroupQry wordGroupQry = new WordGroupQry();
                    wordGroupQry.setGroupId(groupId);
                    wordGroupQry.setPageSize(Integer.MAX_VALUE);
                    int total = 0;
                    int passNum = 0;
                    int gooNum = 0;
                    Integer excellentNum = 0;

                    for(WordModel wordModel : queryWordGroupListPage(wordGroupQry).getItems()){
                        total++;
                        if(wordModel.getLevel() != null) {
                            FamiliarLevelEnum level = FamiliarLevelEnum.valueOfType(wordModel.getLevel());
                            if (level != null) {
                                switch (level) {
                                    case PASS:
                                        passNum++;
                                        break;
                                    case GOOD:
                                        passNum++;
                                        gooNum++;
                                        break;
                                    case EXCELLENT:
                                        passNum++;
                                        gooNum++;
                                        excellentNum++;
                                        break;
                                    default:
                                }
                            }
                        }
                    }
                    // 更新记录
                    WordGroupModel updateWordGroupModel = new WordGroupModel();
                    updateWordGroupModel.setId(groupId);
                    updateWordGroupModel.setWordTotal(total);
                    updateWordGroupModel.setPassRate(passNum*100/total);
                    updateWordGroupModel.setGoodRate(gooNum*100/total);
                    updateWordGroupModel.setExcellentRate(excellentNum*100/total);
                    updateWordGroupModel.setResultTime(new Date());
                    baseMapper.updateById(updateWordGroupModel);
                }
            }
        }
    }
}
