package com.zz.SSM.Plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import com.zz.SSM.Bean.Page;
import com.zz.SSM.Util.Reflections;

/**
 * 
 * @Title:PagePlugin
 * @Description:TODO(分页插件)
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月6日 上午10:00:52
 */
@Intercepts({
	@Signature(type = StatementHandler.class, 
			method = "prepare", 
			args = { Connection.class, Integer.class }) })
public class PagePlugin extends BasePlugin {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 插件默认参数，可配置默认值.
	 */
	private Integer defaultPage; 			// 默认页码
	private Integer defaultPageSize;		// 默认每页条数
	private Boolean defaultUseFlag; 		// 默认是否启用插件
	private Boolean defaultCheckFlag; 		// 默认是否检测页码参数
	private Boolean defaultCleanOrderBy; 	// 默认是否清除最后一个order by 后的语句

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler stmtHandler = (StatementHandler) getUnProxyObject(invocation.getTarget());
		MetaObject metaStatementHandler = SystemMetaObject.forObject(stmtHandler);
		String sql = (String) metaStatementHandler.getValue(DELEGATE_BOUNTSQL_SQL);
		// 不是select语句
		if (!checkSelect(sql)) {
			return invocation.proceed();
		}
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue(DELEGATE_BOUNTSQL);
		Object parameterObject = boundSql.getParameterObject();
		Page<Object> page = getPageParamsForParamObj(parameterObject);
		if (page == null) { // 无法获取分页参数，不进行分页
			return invocation.proceed();
		}

		// 获取配置中是否启用分页功能
		Boolean useFlag = page.getUseFlag() == null ? this.defaultUseFlag : page.getUseFlag();
		if (!useFlag) { // 不使用分页插件
			return invocation.proceed();
		}
		// 获取相关配置的参数
		Integer pageNum = page.getPage() == null ? defaultPage : page.getPage();
		Integer pageSize = page.getPageSize() == null ? defaultPageSize : page.getPageSize();
		Boolean checkFlag = page.getCheckFlag() == null ? defaultCheckFlag : page.getCheckFlag();
		Boolean cleanOrderBy = page.getCleanOrderBy() == null ? defaultCleanOrderBy
				: page.getCleanOrderBy();
		// 计算总条数
		int total = getTotal(invocation, metaStatementHandler, boundSql, cleanOrderBy);
		// 回填总条数到分页参数
		page.setTotal(total);
		// 计算总页数.
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		// 回填总页数到分页参数
		page.setTotalPage(totalPage);
		// 检查当前页码的有效性
		checkPage(checkFlag, pageNum, totalPage);
		// 修改sql
		return preparedSQL(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
	}
	
	/**
	 * 
	 * @Title:plugin
	 * @Description:TODO(生成代理对象)
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 
	 * @Title:setProperties
	 * @Description:TODO(设置插件配置参数)
	 */
	@Override
	public void setProperties(Properties properties) {
		super.initProperties(properties);
		// 从配置中获取参数
		String strDefaultPage = properties.getProperty("default.page");
		String strDefaultPageSize = properties.getProperty("default.pageSize");
		String strDefaultUseFlag = properties.getProperty("default.useFlag");
		String strDefaultCheckFlag = properties.getProperty("default.checkFlag");
		String StringDefaultCleanOrderBy = properties.getProperty("default.cleanOrderBy");
		// 设置默认参数.
		this.defaultPage = Integer.parseInt(strDefaultPage);
		this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
		this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
		this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
		this.defaultCleanOrderBy = Boolean.parseBoolean(StringDefaultCleanOrderBy);
	}
	
	/**
	 * 
	 * @Title:checkSelect
	 * @Description: TODO(判断是否为查询sql语句)
	 * @param sql 当前执行SQL
	 * @return 是否查询语句
	 */
	private boolean checkSelect(String sql) {
		String trimSql = sql.trim();
		int idx = trimSql.toLowerCase().indexOf("select");
		return idx == 0;
	}
	
	/**
	 * 
	 * @Title:getPageParamsForParamObj
	 * @Description: TODO(分离出分页参数)
	 * @param parameterObject 执行参数
	 * @return 分页参数
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<Object> getPageParamsForParamObj(Object parameterObject) throws Exception {
		Page<Object> pageParams = null;
		if (parameterObject == null) {
			return null;
		}
		
		if (parameterObject instanceof Map) {
			/*处理map参数，多个匿名参数和@Param注解参数，都是map*/
			Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
			Set<String> keySet = paramMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = paramMap.get(key);
				if (value instanceof Page) {
					return (Page<Object>) value;
				}
			}
		} else if (parameterObject instanceof Page) { 
			/*参数是或者继承PageParams*/
			return (Page<Object>) parameterObject;
		} else {
			/*从POJO属性尝试读取分页参数*/
			return (Page<Object>)Reflections.getFieldValue(parameterObject, PAGE);
		}
		return pageParams;
	}
	
	/**
	 * 
	 * @Title:getTotal
	 * @Description: TODO(获取总条数)
	 * @param ivt 入参
	 * @param metaStatementHandler statementHandler
	 * @param boundSql sql
	 * @param cleanOrderBy 是否清除order by语句
	 * @return sql查询总数
	 * @throws Throwable
	 */
	private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql, Boolean cleanOrderBy)
			throws Throwable {
		// 获取当前的mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue(DELEGATE_MAPPED_STATEMENT);
		// 配置对象
		Configuration cfg = mappedStatement.getConfiguration();
		// 当前需要执行的SQL
		String sql = (String) metaStatementHandler.getValue(DELEGATE_BOUNTSQL_SQL);
		// 去掉最后的order by语句
		if (cleanOrderBy) {
			sql = this.cleanOrderByForSql(sql);
		}
		// 改写为统计总数的SQL
		String countSql = "select count(*) as total from (" + sql + ") tmp";
		// 获取拦截方法参数，根据插件签名，知道是Connection对象
		Connection connection = (Connection) ivt.getArgs()[0];
		PreparedStatement ps = null;
		int total = 0;
		try {
			// 预编译统计总数SQL
			ps = connection.prepareStatement(countSql);
			// 构建统计总数BoundSql
			BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			// 构建MyBatis的ParameterHandler用来设置总数Sql的参数
			ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(),
					countBoundSql);
			// 设置总数SQL参数
			handler.setParameters(ps);
			// 执行查询.
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} finally {
			// 这里不能关闭Connection，否则后续的SQL就没法继续了
			if (ps != null) {
				ps.close();
			}
		}
		return total;
	}
	
	/**
	 * 
	 * @Title:cleanOrderByForSql
	 * @Description: TODO(去除orderBy子句)
	 * @param sql
	 * @return
	 */
	private String cleanOrderByForSql(String sql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(sql);  
        StringBuffer sb = new StringBuffer();
        while (m.find()) {  
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();  
	}
	
	/**
	 * 
	 * @Title:checkPage
	 * @Description: TODO(检查当前页码的有效性)
	 * @param checkFlag 检测标志
	 * @param pageNum 当前页码
	 * @param pageTotal 最大页码
	 * @throws Throwable
	 */
	private void checkPage(Boolean checkFlag, Integer pageNum, Integer pageTotal) throws Throwable {
		if (checkFlag) {
			// 检查页码page是否合法
			if (pageNum > pageTotal) {
				throw new Exception("查询失败，查询页码【" + pageNum + "】大于总页数【" + pageTotal + "】！！");
			}
		}
	}
	
	/**
	 * 
	 * @Title:preparedSQL
	 * @Description: TODO(预编译改写后的SQL，并设置分页参数)
	 * @param invocation 入参
	 * @param metaStatementHandler MetaObject绑定的StatementHandler
	 * @param boundSql boundSql对象
	 * @param pageNum 当前页
	 * @param pageSize 最大页
	 * @return
	 * @throws Exception
	 */
	private Object preparedSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, int pageNum,
			int pageSize) throws Exception {
		// 分页sql编译
		String newSql = DIALECT.getLimitString(boundSql.getSql(), pageNum, pageSize);
		// 修改当前需要执行的SQL
		metaStatementHandler.setValue(DELEGATE_BOUNTSQL_SQL, newSql);
		// 执行编译，相当于StatementHandler执行了prepared()方法
		return invocation.proceed();
	}
	
}
