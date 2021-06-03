package com.hry.project.dictation.mapper;

import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.model.GroupConfigModel;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/3 19:31
 */
public class GroupConfigMapperTest extends BaseTest {
    @Autowired
    private GroupConfigMapper groupConfigMapper;

    @Test
    public void add(){
        GroupConfigModel model = new GroupConfigModel();
        model.setName("测试");
        model.setCreateTime(new Date());
        model.setNextInterval(1);
        model.setType(1);
        groupConfigMapper.insert(model);

        System.out.println(CommonJsonUtils.toJsonString(groupConfigMapper.selectById(model.getId())));
    }
}
