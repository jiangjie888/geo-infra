package com.geo.framework.domainmodel;

import java.util.List;

/**
 * 分页查询结果对象
 */
public class QueryResult<T> {
    private List<T> result;
    private Long total;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}