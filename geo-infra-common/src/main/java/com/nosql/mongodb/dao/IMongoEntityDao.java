package com.nosql.mongodb.dao;

import com.nosql.mongodb.base.IBaseMongoDao;
import com.nosql.mongodb.model.MongoEntity;


public interface IMongoEntityDao extends IBaseMongoDao<MongoEntity>{
    public MongoEntity getMongoEntityById(String id);
}