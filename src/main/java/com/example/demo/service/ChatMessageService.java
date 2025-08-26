package com.example.demo.service;

import com.example.demo.configuration.entity.ChatMessage;
import com.example.demo.dto.ChatMessageDto;
import com.example.demo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void save(ChatMessageDto chatMessageDto) {
        chatMessageRepository.save(ChatMessage.of(chatMessageDto));
    }
}
