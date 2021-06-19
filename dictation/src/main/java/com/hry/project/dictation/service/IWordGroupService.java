package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordGroupQry;
import com.hry.project.dictation.model.WordGroupListModel;
import com.hry.project.dictation.model.WordGroupModel;
import com.hry.project.dictation.model.WordModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-15
 */
public interface IWordGroupService extends IService<WordGroupModel> {

    /**
     * 查询分组
     * @param qry
     * @return
     */
    MyPage<WordGroupModel> queryWordGroupPage(WordGroupQry qry);

    /**
     * 查询分组内容
     * @param qry
     * @return
     */
    MyPage<WordModel> queryWordGroupListPage(WordGroupQry qry);

    /**
     * 保存组
     * @param entity
     * @param listModelList
     */
    void save(WordGroupModel entity, List<WordGroupListModel> listModelList);

    /**
     * 更新指定分组统计信息
     * @param groupIdArray
     */
    void updateGroupInfo(Long[] groupIdArray);
}
