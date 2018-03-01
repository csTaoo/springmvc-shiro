package com.shitao.sys.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.shitao.common.utils.ShiroFilterUtils;
import com.shitao.sys.dao.FuncDao;

public class PermissionFilter extends AccessControlFilter {
	@Autowired
	private FuncDao funcDao;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
		//先判断带参数的权限判断
		Subject subject = getSubject(request, response);
		if(null != mappedValue){
			String[] arra = (String[])mappedValue;
			for (String permission : arra) {
				if(subject.isPermitted(permission)){
					return Boolean.TRUE;
				}
			}
		}
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		HttpServletResponse httpResponse = ((HttpServletResponse)response);
		
		String uri = httpRequest.getRequestURI();//获取URI
		String mark = funcDao.getMarkByUri(uri);
		String isforbid = funcDao.getFuncStatus(uri);
		if("1".equals(isforbid))
		{
			if(ShiroFilterUtils.isAjax(request)){
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
				httpResponse.flushBuffer();
			}
			else
			{
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			return Boolean.FALSE;
		}
		
		if(subject.isPermitted(mark)){
			return Boolean.TRUE;
		}
		if(ShiroFilterUtils.isAjax(request)){
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.flushBuffer();
		}
		else
		{
			WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return Boolean.FALSE;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		return false;
	}
}