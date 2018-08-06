package com.zz.SSM.Util.Dialect.DB;

import com.zz.SSM.Util.Dialect.Dialect;

/**
 * 
 * @Title:HSQLDialect
 * @Description:TODO(Dialect for HSQLDB)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:26:21
 */
public class HSQLDialect implements Dialect {
	
    @Override
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
    public String getLimitString(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {
        boolean hasOffset = offset > 0;
        return
                new StringBuffer(sql.length() + 10)
                        .append(sql)
                        .insert(sql.toLowerCase().indexOf("select") + 6, hasOffset ? " limit " + offsetPlaceholder + " " + limitPlaceholder : " top " + limitPlaceholder)
                        .toString();
    }

}
