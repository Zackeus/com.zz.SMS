package com.zz.SSM.Util.Dialect.DB;

import com.zz.SSM.Util.Dialect.Dialect;

/**
 * 
 * @Title:OracleDialect
 * @Description:TODO(Oracle的方言实现)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:29:11
 */
public class OracleDialect implements Dialect {
	
	   @Override
	    public boolean supportsLimit() {
	        return true;
	    }

	    @Override
	    public String getLimitString(String sql, int offset, int limit) {
	        return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
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
	    public String getLimitString(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {
	        sql = sql.trim();
	        boolean isForUpdate = false;
	        if (sql.toLowerCase().endsWith(" for update")) {
	            sql = sql.substring(0, sql.length() - 11);
	            isForUpdate = true;
	        }
	        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);

	        if (offset > 0) {
				pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
			} else {
				pagingSelect.append("select * from ( ");
			}
			pagingSelect.append(sql);
			if (offset > 0) {
				String endString = offsetPlaceholder + "+" + limitPlaceholder;
				pagingSelect.append(" ) row_ where rownum <= "+endString+") where rownum_ > ").append(offsetPlaceholder);
			} else {
				pagingSelect.append(" ) where rownum <= "+limitPlaceholder);
			}

	        if (isForUpdate) {
	            pagingSelect.append(" for update");
	        }

	        return pagingSelect.toString();
	    }

}
