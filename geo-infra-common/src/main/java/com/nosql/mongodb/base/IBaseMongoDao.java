package com.nosql.mongodb.base;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;
public interface IBaseMongoDao<T> {
    public T findOne(String id);
    public List<T> find(Query query);
    public Long findCount(Query query);
    public List<T> findList(Integer skip,Integer limit,Query query);
    public void insert(T entity);
    public Integer update(Query query, Update update) throws Exception;
    public Integer update(T entity) throws Exception;
    public Integer remove(T entity);
    public Integer remove(Query query,T entity);
}