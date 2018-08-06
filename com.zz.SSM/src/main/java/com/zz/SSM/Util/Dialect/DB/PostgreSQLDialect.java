package com.zz.SSM.Util.Dialect.DB;

import com.zz.SSM.Util.Dialect.Dialect;

/**
 * 
 * @Title:PostgreSQLDialect
 * @Description:TODO(Postgre Sql的方言实现)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:30:28
 */
public class PostgreSQLDialect implements Dialect {
	
	   public boolean supportsLimit() {
	        return true;
	    }

	    @Override
	    public String getLimitString(String sql, int offset, int limit) {
	        return getLimitString(sql, offset, Integer.toString(offset),
	                Integer.toString(limit));
	    }

	    /**
	     * 
	     * @Title:getLimitString
	     * @Description: TODO(将sql变成分页sql语句,提供将offset及limit使用占位符号(placeholder)替换)
	     * @param sql 实际SQL语句
	     * @param offset 分页开始纪录条数
	     * @param offsetPlaceholder 分页开始纪录条数－占位符号
	     * @param limit 分页每页显示纪录条数
	     * @param limitPlaceholder 分页纪录条数占位符号
	     * @return 包含占位符的分页sql
	     */
	    public String getLimitString(String sql, int offset,
	                                 String offsetPlaceholder, String limitPlaceholder) {
	        StringBuilder pageSql = new StringBuilder().append(sql);
	        pageSql = offset <= 0
	                ? pageSql.append(" limit ").append(limitPlaceholder) :
	                pageSql.append(" limit ").append(limitPlaceholder).append(" offset ").append(offsetPlaceholder);
	        return pageSql.toString();
	    }

}
