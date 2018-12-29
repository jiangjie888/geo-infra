package com.nosql.mongodb.base;

import com.mongodb.WriteResult;
import com.nosql.mongodb.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
public class BaseMongoDao<T> implements  IBaseMongoDao<T>{
    private Class<T> clazz;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoFactory mongoFactory;
    public BaseMongoDao() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
    @Override
    public void insert(T entity) {
        mongoTemplate.insert(entity);
    }
    @Override
    public T findOne(String id) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return getMongoTemplate().findOne(query,clazz);
    }
    @Override
    public List<T> find(Query query) {
        return getMongoTemplate().find(query,clazz);
    }
    @Override
    public Long findCount(Query query) {
        return getMongoTemplate().count(query,clazz);
    }
    @Override
    public List<T> findList(Integer skip, Integer limit,Query query) {
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"createTime")));
        query.skip(skip).limit(limit);
        return find(query);
    }
    @Override
    public Integer update(Query query, Update update) throws Exception {
        WriteResult writeResult = getMongoTemplate().updateFirst(query,update,clazz);
        return (null == writeResult ? 0 : writeResult.getN());
    }
    @Override
    public Integer update(T entity) throws Exception {
        Map<String,Object> map = mongoFactory.converObjectToParams(entity);
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(map.get("id")));
        Update update = (Update) map.get("update");
        return this.update(query,update);
    }
    @Override
    public Integer remove(T entity) {
        WriteResult writeResult = getMongoTemplate().remove(entity);
        return (null == writeResult ? 0 : writeResult.getN());
    }
    @Override
    public Integer remove(Query query, T entity) {
        WriteResult writeResult = getMongoTemplate().remove(query,entity.getClass());
        return (null == writeResult ? 0 : writeResult.getN());
    }
    public MongoTemplate getMongoTemplate(){
        return mongoTemplate;
    }
}