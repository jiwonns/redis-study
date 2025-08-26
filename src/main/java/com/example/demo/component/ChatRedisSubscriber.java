package com.example.demo.component;

import com.example.demo.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ChatRedisSubscriber implements MessageListener {
    private final SimpMessagingTemplate messagingTemplate;
    private final GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

    @Override
    public void onMessage(Message m, byte[] b){
        ChatMessageDto chatMessageDto = (ChatMessageDto) serializer.deserialize(m.getBody());
        ChatMessageDto dto = Objects.requireNonNull(chatMessageDto);
        Long roomId = dto.getRoomId();
        messagingTemplate.convertAndSend("/sub/rooms/" + roomId, dto);
    }
}
