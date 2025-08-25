package com.example.demo.controller;

import com.example.demo.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    // 특정 사용자에게 메시지를 보내는데 사용되는 STOMP을 이용한 템플릿입니다.
    private final SimpMessagingTemplate template;
    private final RedisTemplate<String, Object> redisTemplate;
    /**
     * Message 엔드포인트로 데이터와 함께 호출을 하면 "/sub/message"를 수신하는 사용자에게 메시지를 전달합니다.
     *
     * @param chatMessageDto
     * @return
     */
    @MessageMapping("/messages")
    public ChatMessageDto send(@RequestBody ChatMessageDto chatMessageDto) {
//        if (chatMessageDto.getTyp?e)

        // 구독중인 모든 사용자에게 메시지를 전달합니다.
        template.convertAndSend("/sub/message", chatMessageDto.getContent());

        redisTemplate.convertAndSend("/sub/message", chatMessageDto.getContent());
        return chatMessageDto;
    }
}
