package com.zz.SSM.Util.Dialect.DB;

import com.zz.SSM.Util.Dialect.Dialect;

/**
 * 
 * @Title:SQLServerDialect
 * @Description:TODO(MSSQLServer 数据库实现分页方言)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:31:38
 */
public class SQLServerDialect implements Dialect {
	
    public boolean supportsLimit() {
        return true;
    }

    static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
    }

    public String getLimitString(String sql, int offset, int limit) {
        return getLimit(sql, offset, limit);
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
    public String getLimit(String sql, int offset, int limit) {
        if (offset > 0) {
            throw new UnsupportedOperationException("sql server has no offset");
        }
        return new StringBuffer(sql.length() + 8)
                .append(sql)
                .insert(getAfterSelectInsertPoint(sql), " top " + limit)
                .toString();
    }

}
