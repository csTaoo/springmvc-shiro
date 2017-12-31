package com.shitao.common.paginationer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;



public class DBhelper {
	
	
	/*
	 * 获得记录总数
	 */
	public static int getCount(final String sql,final MappedStatement mappedStatement, final Object parameterObject,
			final BoundSql boundSql) throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try 
		{
			String countSql = "select count(1) from (" +sql+ ") temp_count";
			conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
			ps = conn.prepareStatement(countSql);
			result = ps.executeQuery();
			if(result.next())
			{
				return result.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(result!=null)
			{
				result.close();
			}
			if(ps!=null)
			{
				ps.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}
		
		
		return 0;
	}

}
