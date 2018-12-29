package com.geo.framework.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

import com.geo.framework.domainmodel.*;

/**
 * @author jjie
 * 仓储层数据库操作类
 * @param <T>
 */
public interface IBaseRepository<T extends AuditedEntity> {

	/**
	 * 得到EM，不建议经常使用，以免使用过于随意造成系统的维护难度加大和扩展性变差
	 */
	public EntityManager getEntityManager();

	// 清除一级缓存的数据
	public void clear();

	public List<T> getAll();

	// 获取实体，具有延迟加载的作用（和get相比）
	public T load(Object entityId);

	public T get(Object entityId);

	// 根据where条件查询单个对象
	public T findByWhere(String where, Object[] params);

	// 新增实体
	public void create(T entity);

	// 更新实体
	public void update(T entity);

	//
	public void saveOrUpdate(T entity);

	// 批量新增实体
	public void createBatch(List<T> entitys);

	// 逻辑删除，并不是真的删除。只是失效
	public void physicsDelete(Object entityid);

	// 删除实体
	public void delete(Object entityid);

	// 根据ID集合批量删除实体
	public void delete(Object[] entityids);

	/**
	 * 根据条件删除
	 * @param where
	 * @param delParams
	 */
	public void deleteByWhere(String where, Object[] delParams);

	/**
	 * 根据条件判断实体是否存在
	 * @param whereql
	 *            查询条件(可空,可为 field1=? and field2=? 形式,也可为field1='value1' and
	 *            field2='value2'的形式)
	 * @param queryParams
	 *            参数(可空，但是当条件使用了field1=? and field2=? 的形式后参数不能为空)
	 * @return 是否存在
	 */
	public boolean isExistedByWhere(String whereql, Object[] queryParams);

	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount(Class<T> entityClass);

	/**
	 * 根据条件和参数获取记录总数
	 * 
	 * @param
	 * @param whereql
	 *            查询条件(可空,可为 field1=? and field2=? 形式,也可为field1='value1' and
	 *            field2='value2'的形式)
	 * @param queryParams
	 *            参数(可空，但是当条件使用了field1=? and field2=? 的形式后参数不能为空)
	 * @return 记录行数
	 */
	public long getCountByWhere(String whereql, Object[] queryParams);

	/**
	 * 获取分页数据
	 * 
	 * @param
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @return
	 */
	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby);

	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby);

	public QueryResult<T> queryAllbyPage(int start, int limit, String wherejpql,
			Map<String, Object> queryParams, LinkedHashMap<String, String> orderby);

	/**
	 * 根据where条件查询实体bean列表 <br>
	 * where和queryParams可空
	 * 
	 * @param
	 * @param wheresql
	 * @param queryParams
	 * @return
	 */
	public List<T> queryByWhere(String wheresql, Object[] queryParams);

}