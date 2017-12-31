package com.shitao.sys.message.websocket;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketHandShake implements HandshakeInterceptor {

	public void afterHandshake(ServerHttpRequest severReq,
			ServerHttpResponse serverRes, WebSocketHandler handler, Exception ex) {

	}

	public boolean beforeHandshake(ServerHttpRequest severReq,
			ServerHttpResponse serverRes, WebSocketHandler handler,
			Map<String, Object> attrs) throws Exception {
		Session session = SecurityUtils.getSubject().getSession();
		// 从登录处的session获得用户的用户的标识
		if (serverRes instanceof ServletServerHttpRequest) {
			Long uid = (Long) session.getAttribute("uid");
			if (uid != null)
				attrs.put("uid", uid);
			else
				return false;

		}

		return true;
	}

}
