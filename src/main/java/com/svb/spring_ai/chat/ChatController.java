package com.svb.spring_ai.chat;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    // Create a instance of the Chat client
    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public String chat() {
        return chatClient
                .prompt()
                .user("How is the weather like in Bangalore??")
                .call()
                .content();
    }
}
