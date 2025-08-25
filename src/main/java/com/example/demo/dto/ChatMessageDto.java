package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private String content;
    private String sender;

    public void setEnterMessage() {
        this.content = this.sender + "님이 입장하셨습니다.";
    }
}
