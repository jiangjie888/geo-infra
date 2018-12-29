package com.geo.infra.service.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geo.framework.domainmodel.QueryResult;
import com.geo.infra.dao.IUserDao;
import com.geo.infra.domain.User;
import com.geo.infra.service.IUserService;

//@Component
@Service
public class UserServiceImpl implements IUserService {
	
	//@Autowired
	//@SuppressWarnings("restriction")
	@Resource(name="UserDao")  
    private IUserDao userDao; 
	
	/*@Resource
	private IUserService userService;*/
	
	//获取分页列表
	public QueryResult<User> getList(int start, int limit, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby){
		return userDao.getList(start, limit, wherejpql, queryParams,orderby);
	}
	
	//根据RKEY获取实体
	public User getEntity(String rkey){
		User u = new User();
		u.setName("测试本地Local");
		return u;
		//return userDao.getEntity(rkey);
	}
	
	
	//保存或更新
	public void saveOrUpdate(User entity){
		userDao.saveOrUpdate(entity);
	}
		
	//删除
	public void delete(String rkey){
		userDao.delete(rkey);
	}
	
	
}
