package com.hry.project.dictation.dto.req.word;

import com.hry.project.dictation.dto.req.AbstractQry;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 16:51
 */
public class WordGroupQry extends AbstractQry {
    /**
     * 分组id
     */
    private Long groupId;
    /**
     * 分组名称
     */
    private String name;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
