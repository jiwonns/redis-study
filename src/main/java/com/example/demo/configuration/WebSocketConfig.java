package com.example.demo.configuration;

import com.example.demo.configuration.handler.ChatWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;

    /**
     * WebSocket 연결을 위해서 Handler를 구성합니다.
     *
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                // 클라이언트에서 웹 소켓 연결을 위해 "ws-stomp"라는 엔드포인트로 연결을 시도하면
                // ChatWebSocketHandler 클래스에서 이를 처리합니다.
                .addHandler(new ChatWebSocketHandler(), "/ws-stomp")
                // 접속 시도하는 모든 도메인 또는 IP에서 WebSocket 연결을 허용합니다.
                .setAllowedOrigins("*")
                // SockJS를 사용하여 WebSocket 연결을 지원하지 않는 클라이언트에서도 연결할 수 있도록 합니다.
                .withSockJS();
    }


}
