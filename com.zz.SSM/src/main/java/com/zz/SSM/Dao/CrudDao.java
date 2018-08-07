package com.zz.SSM.Dao;

import java.util.List;

/**
 * 
 * @Title:CrudDao
 * @Description:TODO(DAO支持类实现)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月7日 上午8:52:57
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 
	 * @Title:get
	 * @Description: TODO(获取单条数据)
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 
	 * @Title：get
	 * @Description: TODO(获取单条数据)
	 * @see：
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	/**
	 * 
	 * @Title：findList
	 * @Description: TODO(查询数据列表，如果需要分页，请设置分页对象)
	 * @see：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 
	 * @Title：findAllList
	 * @Description: TODO(查询所有数据列表)
	 * @see：
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	
	/**
	 * 
	 * @Title：findAllList
	 * @Description: TODO(查询所有数据列表)
	 * @see：public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();
	
	/**
	 * 
	 * @Title：insert
	 * @Description: TODO(插入数据)
	 * @see：
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 
	 * @Title：update
	 * @Description: TODO(更新数据)
	 * @see：
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 
	 * @Title：delete
	 * @Description: TODO(删除数据（一般为逻辑删除，更新del_flag字段为1）)
	 * @see：public int delete(T entity)
	 * @param id
	 * @return
	 */
	@Deprecated
	public int delete(String id);
	
	/**
	 * 
	 * @Title：delete
	 * @Description: TODO(删除数据（一般为逻辑删除，更新del_flag字段为1)
	 * @see：
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
}