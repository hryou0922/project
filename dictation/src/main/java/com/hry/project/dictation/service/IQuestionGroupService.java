package com.hry.project.dictation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.question.QuestionGroupQry;
import com.hry.project.dictation.model.QuestionGroupListModel;
import com.hry.project.dictation.model.QuestionGroupModel;
import com.hry.project.dictation.model.QuestionModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hry
 * @since 2021-06-28
 */
public interface IQuestionGroupService extends IService<QuestionGroupModel> {

    QuestionGroupModel selectWrodGroupByGroupName(String name);

    /**
     * 查询分组
     * @param qry
     * @return
     */
    MyPage<QuestionGroupModel> queryQuestionGroupPage(QuestionGroupQry qry);

    /**
     * 查询分组内容
     * @param qry
     * @return
     */
    MyPage<QuestionModel> queryQuestionGroupListPage(QuestionGroupQry qry);

    /**
     * 判断
     * @param groupId
     * @param questionId
     * @return
     */
    boolean isQuestionInGroup(long groupId, String questionId);

    /**
     * 创建一个临时听写组
     * @param groupName
     * @param questionIdList
     */
    void creatTmpQuestionGroup(String groupName, List<String> questionIdList);

    /**
     * 删除组
     * @param groupId
     */
    void deleteQuestionGroupById(long groupId);

    /**
     * 保存组
     * @param entity
     * @param modelList
     */
    void save(QuestionGroupModel entity, List<QuestionGroupListModel> modelList);

    /**
     * 更新指定分组统计信息
     * @param groupIdArray
     */
    void updateGroupInfo(Long[] groupIdArray);
}
