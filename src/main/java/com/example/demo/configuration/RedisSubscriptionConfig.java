package com.example.demo.configuration;

import com.example.demo.component.ChatRedisSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

// 레디스 쪽에서 구독
@Configuration
@RequiredArgsConstructor
public class RedisSubscriptionConfig {

    private final RedisMessageListenerContainer container;
    private final ChatRedisSubscriber subscriber;

    @PostConstruct
    public void subscribe() {
        container.addMessageListener(subscriber, new PatternTopic("chat:room:*")); // ✅ 구독 등록
    }
}