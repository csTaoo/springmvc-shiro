package com.shitao.common.paginationer;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


@Intercepts({@Signature(type = Executor.class, method = "query",
args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		
		if(PageHelper.isExePage())
		{
			//获得sql语句
			final MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
			
			Object paramObject = invocation.getArgs()[1];
			BoundSql boundSql = mappedStatement.getBoundSql(paramObject);
			//获得参数
			Object paramSql = boundSql.getParameterObject();
			/*
			 * 修改sql语句
			 * BoundSql并没有提供修改sql语句的方法
			 * 只能使用反射了
			 */
			Page page = PageHelper.getPage();
			Class<? extends BoundSql> boundSqlClass = boundSql.getClass();
			//获取所有定义的字段  包括private
			Field fields = boundSqlClass.getDeclaredField("sql");
			fields.setAccessible(true);
			String sql = (String) fields.get(boundSql);
			int count = DBhelper.getCount(sql, mappedStatement, paramSql, boundSql);
			page.setCount(count);
			
			//分页语句
			StringBuilder sqlPage = new StringBuilder();
			sqlPage.append(sql).append(" limit ").append(page.getStartRow()+","+page.getPageNum());
			
			//分页 
			SqlSource sqlsource = new StaticSqlSource(mappedStatement.getConfiguration(), sqlPage.toString());
			Field mappstatmentField = mappedStatement.getClass().getDeclaredField("sqlSource");
			mappstatmentField.setAccessible(true);
			mappstatmentField.set(mappedStatement, sqlsource);
			mappstatmentField.setAccessible(false);
		}
		
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return  Plugin.wrap(target, this);
	}

	
	public void setProperties(Properties properties) {
		
		
	}

}
