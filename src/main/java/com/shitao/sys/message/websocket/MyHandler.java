package com.shitao.sys.message.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MyHandler implements WebSocketHandler{

	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		
		System.out.println("ss");
		
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
	}

	public boolean supportsPartialMessages() {
		return false;
	}
	
	
	

}
