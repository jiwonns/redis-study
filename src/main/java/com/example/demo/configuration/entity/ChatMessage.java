package com.example.demo.configuration.entity;

import com.example.demo.dto.ChatMessageDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;
    private Long roomId;
    private String sender;
    @Column(length = 1000)
    private String content;
    private LocalDateTime sentAt;

    public static ChatMessage of(ChatMessageDto d) {
        var m = new ChatMessage();
        m.roomId = d.getRoomId();
        m.sender = d.getSender();
        m.content = d.getContent();
        m.sentAt = d.getSentAt() != null ? d.getSentAt() : LocalDateTime.now();
        return m;
    }
}
