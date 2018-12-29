package com.geo.infra.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.geo.framework.domainmodel.QueryResult;
import com.geo.framework.repository.impl.*;
import com.geo.infra.dao.IUserDao;
import com.geo.infra.domain.User;

@SuppressWarnings("all")
@Repository("UserDao")
public class UserDaoImpl extends BaseRepository<User> implements IUserDao {

	//EntityManagerFactory是线程安全的，但EntityManager不是，每次使用都要重新创建，通过@PersistenceContext 来解决
	/*private EntityManagerFactory emf;  
	
    @PersistenceUnit  
    public void setEntityManagerFactory(EntityManagerFactory emf) {  
        this.emf = emf;  
    }  */
    
    @PersistenceContext  
    private EntityManager em;  
    
    
	//entityManagerFactory
	public EntityManager getEntityManager(){
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		//return emf.createEntityManager(); 
		return em;
	}
	
	//获取分页列表
	public QueryResult<User> getList(int start, int limit, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby){
		return super.queryAllbyPage(start, limit, wherejpql, queryParams,orderby);
	}
	
	//根据RKEY获取实体
	public User getEntity(String rkey){
		return super.get(rkey);
	}
	
	
	//保存或更新
	public void saveOrUpdate(User entity){
		super.saveOrUpdate(entity);
	}
		
	//删除
	public void delete(String rkey){
		super.delete(rkey);
	}
}
