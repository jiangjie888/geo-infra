package com.geo.infra.dao;

import java.util.LinkedHashMap;

import com.geo.framework.domainmodel.QueryResult;
import com.geo.framework.repository.*;
import com.geo.infra.domain.*;


public interface IUserDao extends IBaseRepository<User> {
	
	//获取分页列表
	public QueryResult<User> getList(int start, int limit, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	//根据RKEY获取实体
    public User getEntity(String rkey);
    
    
	//保存或更新
	public void saveOrUpdate(User entity);
		
	//删除
	public void delete(String rkey);
}
