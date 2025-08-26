package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long roomId;
    private String content;
    private String sender;
    private LocalDateTime sentAt;

    public void setEnterMessage() {
        this.content = this.sender + "님이 입장하셨습니다.";
    }
}
