package com.svb.spring_ai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @Autowired
    public ChatClient.Builder chatClient;

    @GetMapping("/chat-bank")
    public String chat(@RequestParam String message) {
        String systemIstruction = """
                You are a Bank Customer Care executive
                You can only discuss 
                - Bank related queries
                - Working hours of the bank
                - General banking queries
                
                If asked about anything else respond: "I can only help with banking queries"
                """;


        return chatClient
                .build()
                .prompt()
                .user(message)
                .system(systemIstruction)
                .call()
                .content();
    }
}
