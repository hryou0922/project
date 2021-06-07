package com.hry.project.dictation.dto.page;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * 分页类
 * Created by Administrator on 2017/9/6.
 */
public class MyPage<T> extends CommonRsp {

    private Integer total = 0;//记录总数

    private Collection<T> items = Collections.emptyList();//本次返回的数据列表

    public static <T> MyPage<T> create(Collection<T> rows, Integer total) {
        if (CollectionUtils.isEmpty(rows)) {
            return new MyPage<>();
        }

        MyPage<T> page = new MyPage<>();
        page.setItems(rows);
        if (total == null) {
            total = rows.size();
        }
        page.setTotal(total);
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }
}
