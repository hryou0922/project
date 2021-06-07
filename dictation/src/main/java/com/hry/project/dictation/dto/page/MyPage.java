package com.hry.project.dictation.dto.page;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * 分页类
 * Created by Administrator on 2017/9/6.
 */
public class MyPage<T> extends CommonRsp {

    private Integer totalCount = 0;//记录总数

    private Collection<T> rows = Collections.emptyList();//本次返回的数据列表

    public static <T> MyPage<T> create(Collection<T> rows, Integer total) {
        if (CollectionUtils.isEmpty(rows)) {
            return new MyPage<>();
        }

        MyPage<T> page = new MyPage<>();
        page.setRows(rows);
        if (total == null) {
            total = rows.size();
        }
        page.setTotalCount(total);
        return page;
    }

    public static <T> MyPage<T> create(Collection<T> rows) {
        return create(rows, null);
    }

    public static <T> MyPage<T> create(int code, String message) {
        MyPage<T> page = new MyPage<>();
        page.setError(code, message);
        return page;
    }

    public void setError(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }
}
