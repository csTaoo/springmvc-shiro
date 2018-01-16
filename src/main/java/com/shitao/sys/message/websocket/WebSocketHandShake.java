package com.shitao.sys.message.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.shitao.common.utils.UserUtils;
import com.shitao.sys.entity.User;

public class WebSocketHandShake implements HandshakeInterceptor {
	
	public void afterHandshake(ServerHttpRequest severReq,
			ServerHttpResponse serverRes, WebSocketHandler handler, Exception ex) {

	}

	public boolean beforeHandshake(ServerHttpRequest severReq,
			ServerHttpResponse serverRes, WebSocketHandler handler,
			Map<String, Object> attrs) throws Exception {
		User user = UserUtils.gerCurrentUser();
		// 从登录处的session获得用户的用户的标识
		if (serverRes instanceof ServletServerHttpRequest) {
			String username = user.getName();
			if (username != null)
				attrs.put("uid", username);
			else
				return false;
		}
		return true;
	}

}
