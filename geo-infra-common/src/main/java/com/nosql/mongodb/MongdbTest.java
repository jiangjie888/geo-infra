package com.nosql.mongodb;

import com.nosql.mongodb.dao.IMongoEntityDao;
import com.nosql.mongodb.model.MongoEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongodb.xml")
public class MongdbTest {
    @Autowired
    private IMongoEntityDao mongoEntityDao;
    /**
     * 根据主键获取实体
     */
    @Test
    public void testFindOne(){
        MongoEntity mongoEntity = mongoEntityDao.getMongoEntityById("593e3f8ba4977227581b398c");
        System.out.println(mongoEntity);
    }
    /**
     * 根据过滤条件获取集合列表
     */
    @Test
    public void testFind(){
        Query query = new Query();
        List<MongoEntity> list1 = mongoEntityDao.find(query);
        System.out.println(list1.size());
        query = new Query();
        query.addCriteria(new Criteria("InterfaceMethod").nin("InterfaceMethod5"));
        List<MongoEntity> list2 = mongoEntityDao.find(query);
        for(MongoEntity mongoEntity : list2){
            System.out.println(mongoEntity);
        }
    }
    /**
     * 根据过滤条件获取查询个数
     */
    @Test
    public void testFindCount(){
        Query query = new Query();
        query.addCriteria(new Criteria("InterfaceMethod").nin("InterfaceMethod5"));
        System.out.println(mongoEntityDao.findCount(query));
    }
    /**
     * 根据过滤条件获取分页集合
     * skip:从第几条开始，但不包括这条数据
     * limit:每页几条数据
     */
    @Test
    public void testFindList(){
        Query query = new Query();
        query.addCriteria(new Criteria("InterfaceMethod").nin("InterfaceMethod11"));
        List<MongoEntity> list = mongoEntityDao.findList(10,5,query);
        for(MongoEntity mongoEntity : list){
            System.out.println(mongoEntity.getBusinessName());
        }
    }
    /**
     * 实体新增
     */
    @Test
    public void testInsert(){
        for(int i = 7; i < 30 ; i++){
            MongoEntity mongoEntity = new MongoEntity();
            mongoEntity.setInterfaceMethod("InterfaceMethod" + i);
            mongoEntity.setBusinessName("BusinessName" + i);
            mongoEntity.setClientReqIpPort("ClientReqIpPort" + i);
            mongoEntity.setServiceRepIpPort("ServiceRepIpPort" + i);
            mongoEntity.setLogInfo("LogInfo" + i);
            Long time1 = new Date().getTime();
            mongoEntity.setClientReqTime(time1);
            Long time2 = new Date().getTime();
            mongoEntity.setServiceRepTime(time2);
            mongoEntity.setDuration(time2 - time1);
            mongoEntity.setClientReqIpPort("ClientReqIpPort" + i);
            mongoEntity.setServiceRepIpPort("ServiceRepIpPort" + i);
            mongoEntity.setIfSuccess("1");
            mongoEntity.setCreateTime(new Date().getTime());
            mongoEntityDao.insert(mongoEntity);
        }
    }
    /**
     * 通过传入的实体ID更新实体中的其他内容
     * @throws Exception
     */
    @Test
    public void testUpdateEntity() throws Exception{
        MongoEntity mongoEntity = mongoEntityDao.findOne("593e3f8ba4977227581b398c");
        System.out.println("1------------->" + mongoEntity.getBusinessName());
        mongoEntity.setBusinessName("setBusinessNametest");
        mongoEntityDao.update(mongoEntity);
        mongoEntity = mongoEntityDao.findOne("593e3f8ba4977227581b398c");
        System.out.println("2------------->" + mongoEntity.getBusinessName());
    }
    /**
     * 删除实体对象
     */
    @Test
    public void testRemove(){
        MongoEntity mongoEntity = mongoEntityDao.findOne("593e4f1ca4977223d8f705aa");
        System.out.println(mongoEntity);
        mongoEntityDao.remove(mongoEntity);
        mongoEntity = mongoEntityDao.findOne("593e4f1ca4977223d8f705aa");
        System.out.println(mongoEntity);
    }
    /**
     * 通过过滤条件和实体对象删除对应数据
     */
    @Test
    public void testRemoveByQuery(){
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is("593e4f1ca4977223d8f705a3"));
        MongoEntity entity = new MongoEntity();
        System.out.println(mongoEntityDao.remove(query,entity));
    }
}