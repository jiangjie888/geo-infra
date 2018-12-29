package com.geo.framework.repository.impl;

import org.apache.commons.lang3.*;
//import org.springframework.stereotype.Repository;

import com.geo.framework.common.*;
import com.geo.framework.domainmodel.AuditedEntity;
import com.geo.framework.domainmodel.QueryResult;
import com.geo.framework.repository.*;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityManager;
//import org.springframework.stereotype.Component;

//import org.apache.commons.lang.StringUtils;
//import org.springframework.transaction.annotation.Transactional;

/**
 * @author jjie
 * 仓储层数据库操作类
 * @param <T>
 */
@SuppressWarnings("all")
//@Component
public abstract class BaseRepository<T extends AuditedEntity> implements IBaseRepository<T> {

	// 申明参数化类型参数的class对象
	private Class<T> entityClass;

	{
		// 获取调用对象的参数化类型
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获取参数化类型对象的实际参数Class对象
		entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];

	}

	private QLBuilder sqlBuilder = new QLBuilder();

	/// @Transactional表是单元事务
	// 返回实体管理器
	public abstract EntityManager getEntityManager();

	protected void setQueryParams(Query query, Object queryParams) {
		sqlBuilder.setQueryParams(query, queryParams);
	}

	// 清除一级缓存的数据

	public void clear() {
		getEntityManager().clear();
	}

	public List<T> getAll() {
		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager().createQuery("SELECT o FROM " + entityname + " o ");
		// 设置二级缓存，对于常用查询
		// query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	// 获取实体，具有延迟加载的作用（和get相比）
	public T load(Object entityId) {
		try {
			return getEntityManager().getReference(entityClass, entityId);
		} catch (Exception e) {
			return null;
		}
	}

	public T get(Object entityId) {
		return getEntityManager().find(entityClass, entityId);
	}

	// 根据where条件查询单个对象
	public T findByWhere(String where, Object[] params) {
		List<T> l = queryByWhere(where, params);
		if (l != null && l.size() == 1) {
			return l.get(0);
		} else if (l.size() > 1) {
			throw new RuntimeException("查寻到的结果不止一个.");
		} else {
			return null;
		}
	}

	// 新增实体
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	// 批量新增实体
	public void createBatch(List<T> entitys) {
		for (T entity : entitys) {
			create(entity);
		}
	}

	// 更新实体
	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	//
	public void saveOrUpdate(T entity) {
		if (entity.getPrimaryKey() == null) {
			this.create(entity);
		} else {
			this.update(entity);
		}
	}

	// 逻辑删除，并不是真的删除。只是失效
	public void physicsDelete(Object entityid) {
		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager().createQuery("SELECT o FROM " + entityname + " o ");
		query.executeUpdate();
	}

	// 删除实体
	public void delete(Object entityid) {
		getEntityManager().remove(getEntityManager().find(entityClass, entityid));
	}

	// 根据ID集合批量删除实体
	public void delete(Object[] entityids) {
		// StringBuffer sf_QL = new StringBuffer(" DELETE FROM ").append(
		// sqlBuilder.getEntityName(entityClass)).append(" o WHERE ")
		// .append(sqlBuilder.getPkField(entityClass, "o")).append("=? ");
		// Query query = getEntityManager().createQuery(sf_QL.toString());
		for (Object id : entityids) {
			this.delete(id);
			// getEntityManager().remove(getEntityManager().find(entityClass,
			// id));
			// query.setParameter(1, id).executeUpdate();
		}
	}

	/**
	 * 根据条件删除
	 * 
	 * @param where
	 * @param delParams
	 */
	public void deleteByWhere(String where, Object[] delParams) {
		StringBuffer sf_QL = new StringBuffer("DELETE FROM ").append(sqlBuilder.getEntityName(entityClass))
				.append(" o WHERE 1=1 ");
		if (where != null && where.length() != 0) {
			sf_QL.append(" AND ").append(where);
		}
		Query query = getEntityManager().createQuery(sf_QL.toString());
		this.setQueryParams(query, delParams);

		query.executeUpdate();
	}

	/**
	 * 根据条件判断实体是否存在
	 * 
	 * @param whereql
	 *            查询条件(可空,可为 field1=? and field2=? 形式,也可为field1='value1' and
	 *            field2='value2'的形式)
	 * @param queryParams
	 *            参数(可空，但是当条件使用了field1=? and field2=? 的形式后参数不能为空)
	 * @return 是否存在
	 */
	public boolean isExistedByWhere(String whereql, Object[] queryParams) {
		long count = getCountByWhere(whereql, queryParams);
		return count > 0 ? true : false;
	}

	/**
	 * 获取记录总数
	 * 
	 * @return
	 */
	public long getCount(Class<T> entityClass) {
		return getCountByWhere(null, null);
	}

	/**
	 * 根据条件和参数获取记录总数
	 * 
	 * @param whereql
	 *            查询条件(可空,可为 field1=? and field2=? 形式,也可为field1='value1' and
	 *            field2='value2'的形式)
	 * @param queryParams
	 *            参数(可空，但是当条件使用了field1=? and field2=? 的形式后参数不能为空)
	 * @return 记录行数
	 */
	public long getCountByWhere(String whereql, Object[] queryParams) {
		StringBuffer sf_QL = new StringBuffer("SELECT COUNT(").append(sqlBuilder.getPkField(entityClass, "o"))
				.append(") FROM ").append(sqlBuilder.getEntityName(entityClass)).append(" o WHERE 1=1 ");
		if (whereql != null && whereql.length() != 0) {
			sf_QL.append(" AND ").append(whereql);
		}
		Query query = getEntityManager().createQuery(sf_QL.toString());
		this.setQueryParams(query, queryParams);
		return (Long) query.getSingleResult();
	}

	/**
	 * 获取分页数据
	 * 
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @return
	 */
	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		 return this.scroll(start, limit, wherejpql,queryParams, orderby);
	}

	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		 Object[] ps = null;
		    if (queryParams != null) {
		        ps = queryParams.toArray();
		    }
		return this.queryAllbyPage(start, limit, wherejpql, ps, orderby);
	}

	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql, Map<String, Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return this.scroll(start, limit, wherejpql,queryParams, orderby);
	}

	private QueryResult<T> scroll(int start, int limit,  String wherejpql, Object queryParams,
			LinkedHashMap<String, String> orderby) {
		QueryResult<T> result = new QueryResult<T>();
		String entityname = sqlBuilder.getEntityName(entityClass);
		
		Query query = getEntityManager().createQuery("SELECT o FROM " + entityname + " o "
				+ (StringUtils.isEmpty(wherejpql) ? "" : "WHERE " + wherejpql) + sqlBuilder.buildOrderby(orderby));
		this.setQueryParams(query, queryParams);
		if (start != -1 && limit != -1)
			query.setFirstResult(start).setMaxResults(limit).setHint("org.hibernate.cacheable", true);
		result.setResult(query.getResultList());
		
		query = getEntityManager().createQuery("SELECT COUNT(" + sqlBuilder.getPkField(entityClass, "o") + ") FROM "
				+ entityname + " o " + (StringUtils.isEmpty(wherejpql) ? "" : "WHERE " + wherejpql));
		this.setQueryParams(query, queryParams);
		result.setTotal((Long) query.getSingleResult());
		
		return result;
	}

	/**
	 * 根据where条件查询实体bean列表 <br>
	 * where和queryParams可空
	 * 
	 * @param wheresql
	 * @param queryParams
	 * @return
	 */
	public List<T> queryByWhere(String wheresql, Object[] queryParams) {
		String entityname = sqlBuilder.getEntityName(entityClass);
		Query query = getEntityManager().createQuery("SELECT o FROM " + entityname + " o "
				+ ((wheresql == null || wheresql.length() == 0) ? "" : "WHERE " + wheresql));
		this.setQueryParams(query, queryParams);
		// query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

}
