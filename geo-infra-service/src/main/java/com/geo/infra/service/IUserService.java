package com.geo.infra.service;

import java.util.LinkedHashMap;

import com.geo.framework.domainmodel.QueryResult;
import com.geo.infra.domain.User;

public interface IUserService {
	
    //@Resource
    //private RedisClient redisClient;
	//获取分页列表
	public QueryResult<User> getList(int start, int limit, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	//根据RKEY获取实体
	public User getEntity(String rkey);
	
	
	//保存或更新
	public void saveOrUpdate(User entity);
		
	//删除
	public void delete(String rkey);
	
	
    
    /**
     * 测试发送mq
     *//*
    public void sendMq(){
        AcctBooks books = new AcctBooks();
        books.setOid("11111111111111111");
        books.setAccountOid("222222222222222");
        books.setBalance(new BigDecimal(3333));
        books.setOpeningBalance(new BigDecimal(4444444));
        books.setRelative("5555555555555");
        RocketMQMessageUtil.sendMQChangeInfo(producer, JsonUtils.writeValue(books),topic,tag);

    }

    *//**
     * 提交缓存
     *//*
    public void setRedis(){
        AcctBooks books = new AcctBooks();
        HashMap<String,String> redisMap = new HashMap<String,String>();
        redisMap.put("testOne","传说我是神");
        redisMap.put("testTwo","我特么就是神");
        String key = RedisKeyConstant.TESTREDISONE+"111111";
        this.redisClient.hmsetString(key, redisMap);

        System.out.println(redisClient.hgetAllString(RedisKeyConstant.TESTREDISONE + "111111"));
    }

    *//**
     * 查询缓存
     *//*
    public void getRedis(){

        String key = RedisKeyConstant.TESTREDISONE+"111111";
        Map<String, String> map = redisClient.hgetAllString(key);
        System.out.print(JsonUtils.writeValue(map));
    }*/
}
