package com.shitao.sys.message.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

		registry.addHandler(myHandler(), "/payMessageHandler")
				.addInterceptors(new WebSocketHandShake());

	}

	public WebSocketHandler myHandler() {
		return new PayMessageHandler();
	}

}
