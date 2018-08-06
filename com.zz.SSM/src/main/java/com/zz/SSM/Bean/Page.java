package com.zz.SSM.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Title:Page
 * @Description:TODO(分页实体)
 * @Company:
 * @author zhou.zhang
 * @param <T>
 * @date 2018年8月6日 上午9:27:28
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page; 			// 当前页码
	private Integer pageSize; 		// 每页限制条数
	private Boolean useFlag; 		// 是否启动插件。如果不启动，则不作分页
	private Boolean checkFlag; 		// 是否检测页码有效性，如果为true，则页码大于最大页数，则抛出异常
	private Boolean cleanOrderBy; 	// 是否清除最后order by后面的语句
	private Integer total;			// 总条数，插件会回填这个值
	private Integer totalPage; 		// 总页数，插件会回填这个值
	private List<T> list;			// 分页实体数据

	public Page() {
		super();
	}
	
	/**
	 * 构造方法(初始化显示条数)
	 * @param pageSize 限制条数
	 */
	public Page(Integer pageSize) {
		this(null, pageSize, null, null, null, null, null);
	}
	
	/**
	 * 构造方法(初始化页码和显示条数)
	 * @param page 页码
	 * @param pageSize 限制条数
 	 */
	public Page(Integer page, Integer pageSize) {
		this(page, pageSize, null, null, null, null, null);
	}
	
	public Page(Integer page, Integer pageSize, Boolean useFlag, Boolean checkFlag, Boolean cleanOrderBy, Integer total,
			Integer totalPage) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.useFlag = useFlag;
		this.checkFlag = checkFlag;
		this.cleanOrderBy = cleanOrderBy;
		this.total = total;
		this.totalPage = totalPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Boolean getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Boolean getCleanOrderBy() {
		return cleanOrderBy;
	}

	public void setCleanOrderBy(Boolean cleanOrderBy) {
		this.cleanOrderBy = cleanOrderBy;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", pageSize=" + pageSize + ", useFlag=" + useFlag + ", checkFlag=" + checkFlag
				+ ", cleanOrderBy=" + cleanOrderBy + ", total=" + total + ", totalPage=" + totalPage + "]";
	}
	
}
