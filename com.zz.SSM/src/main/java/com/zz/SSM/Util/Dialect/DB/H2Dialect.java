package com.zz.SSM.Util.Dialect.DB;

import com.zz.SSM.Util.Dialect.Dialect;

/**
 * 
 * @Title:H2Dialect
 * @Description:TODO(A dialect compatible with the H2 database.)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 下午4:24:55
 */
public class H2Dialect implements Dialect {
	
    public boolean supportsLimit() {
        return true;
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
    private String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        return sql + ((offset > 0) ? " limit " + limitPlaceholder + " offset "
                + offsetPlaceholder : " limit " + limitPlaceholder);
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
    }

}
