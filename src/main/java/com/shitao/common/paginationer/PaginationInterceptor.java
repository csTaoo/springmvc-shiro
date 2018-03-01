package com.shitao.common.paginationer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;


@Intercepts( {
    @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}) })
public class PaginationInterceptor implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		
		if(PageHelper.isExePage())
		{
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		    //通过反射获取到当前RoutingStatementHandler对象的delegate属性
		    StatementHandler delegate = (StatementHandler)getField(handler, "delegate");
		    String sql = delegate.getBoundSql().getSql();
		    System.out.println(sql);
			/*
			 * 修改sql语句
			 * BoundSql并没有提供修改sql语句的方法
			 * 只能使用反射了
			 */
			Page page = PageHelper.getPage();
			//获取所有定义的字段  包括private
			
		}
		
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return  Plugin.wrap(target, this);
	}

	
	public void setProperties(Properties properties) {
		
		
	}
	
	
	private Object getField(Object target,String field)
	{
		Field d = null;
		try 
		{
			d = target.getClass().getDeclaredField(field);
			d.setAccessible(true);
			return d.get(target);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

}
