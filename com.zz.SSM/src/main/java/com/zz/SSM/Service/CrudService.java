package com.zz.SSM.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zz.SSM.Bean.BaseEntity;
import com.zz.SSM.Bean.Page;
import com.zz.SSM.Dao.CrudDao;

/**
 * 
 * @Title:CrudService
 * @Description:TODO(Service基类)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月7日 上午9:11:34
 */
public abstract class CrudService<D extends CrudDao<T>, T extends BaseEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 
	 * @Title：get
	 * @Description: TODO(获取单条数据)
	 * @see：
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 
	 * @Title：get
	 * @Description: TODO(获取单条数据)
	 * @see：
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 
	 * @Title：findList
	 * @Description: TODO(查询列表数据)
	 * @see：
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 
	 * @Title：findPage
	 * @Description: TODO(查询分页数据)
	 * @see：分页对象
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}
	
	/**
	 * 
	 * @Title：save
	 * @Description: TODO(保存数据（插入或更新）)
	 * @see： 
	 * 1.isNewRecord：false；id：null；操作：insert；id：UUID (默认)
	 * 2.isNewRecord：true；id：null；操作：insert；id：null (强制插入)
	 * 3.isNewRecord：true；id：id；操作：insert；id：id (自定义ID)
	 * 4.isNewRecord：false；id：id；操作：update；
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}

	/**
	 * 
	 * @Title：delete
	 * @Description: TODO(删除数据)
	 * @see：
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
