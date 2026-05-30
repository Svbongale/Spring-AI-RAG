package com.svb.spring_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatControllerServiceImpl implements ChatControllerService {

    public final ChatClient chatClient;

    public ChatControllerServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public String chat() {
        return chatClient
                .prompt()
                .user("How is the weather like in Bangalore??")
                .call()
                .content();
    }

    @Override
    public Flux<String> streamChat() {
        return chatClient
                .prompt()
                .user("Tell me a joke about spring-ai")
                .stream()
                .content();
    }
}
