package com.nosql.mongodb.dao;

import com.nosql.mongodb.base.BaseMongoDao;
import com.nosql.mongodb.model.MongoEntity;
import org.springframework.stereotype.Repository;
@Repository
public class MongoEntityDao extends BaseMongoDao<MongoEntity> implements IMongoEntityDao{
    @Override
    public MongoEntity getMongoEntityById(String id) {
        MongoEntity mongoEntity = findOne(id);
        return mongoEntity;
    }
}