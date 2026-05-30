package com.svb.spring_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatControllerServiceImpl implements ChatControllerService {

    @Autowired
    public ChatClient.Builder chatClient;

    @Override
    public String chat() {
        return chatClient
                .build()
                .prompt()
                .user("How is the weather like in Bangalore??")
                .call()
                .content();
    }

    @Override
    public Flux<String> streamChat() {
        return chatClient
                .build()
                .prompt()
                .user("Tell me a joke about spring-ai")
                .stream()
                .content();
    }
}
