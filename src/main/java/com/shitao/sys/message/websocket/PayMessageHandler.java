package com.shitao.sys.message.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class PayMessageHandler implements WebSocketHandler{
	
	private static final Map<String,WebSocketSession> SEESION_MAP;
	
	static
	{
		SEESION_MAP = new HashMap<String,WebSocketSession>();
	}
	
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String uid = (String) session.getAttributes().get("uid");
		if(!SEESION_MAP.containsKey(uid))
		{
			SEESION_MAP.put((String) session.getAttributes().get("uid"), session);
		}
		
	}

	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		
		//获得消息
		String mes =  message.getPayload().toString();
		System.out.println(mes);
	}

	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
	}

	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		String uid = (String) session.getAttributes().get("uid");
		if(SEESION_MAP.containsKey(uid))
		{
			SEESION_MAP.remove(uid);
		}
	}

	public boolean supportsPartialMessages() {
		return false;
	}
	
	
	public static void notifyPayMes(String mes)
	{
		Session session = SecurityUtils.getSubject().getSession();
		String uid = (String) session.getAttribute("uid");
		WebSocketSession wsSession = SEESION_MAP.get(uid);
		try 
		{
			wsSession.sendMessage(new TextMessage(mes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
