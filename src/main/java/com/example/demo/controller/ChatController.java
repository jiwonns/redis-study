package com.example.demo.controller;

import com.example.demo.dto.ChatMessageDto;
import com.example.demo.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    // 특정 사용자에게 메시지를 보내는데 사용되는 STOMP을 이용한 템플릿입니다.
//    private final SimpMessagingTemplate template;
//    private final SimpMessageSendingOperations messagingTemplate;

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatMessageService chatService;

    /**
     * Message 엔드포인트로 데이터와 함께 호출을 하면 "/sub/message"를 수신하는 사용자에게 메시지를 전달합니다.
     *
     * @param roomId
     * @return
     */
    @MessageMapping("/rooms/{roomId}/messages")
    public void send(@DestinationVariable Long roomId, ChatMessageDto dto) {
//        if (chatMessageDto.getTyp?e)

//        chatMessageSaveService.save(chatMessageDto) // db저장

        // 구독중인 모든 사용자에게 메시지를 전달합니다.
        dto.setRoomId(roomId);
        chatService.save(dto);
        redisTemplate.convertAndSend("chat:room:" + roomId, dto); // Redis Pub
    }
}
