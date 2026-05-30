package com.svb.spring_ai.controller;

import com.svb.spring_ai.service.ChatControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Autowired
    ChatControllerService chatControllerService;

    @GetMapping("/chat")
    public String chat() {
       return chatControllerService.chat();
    }

    @GetMapping("/streamChat")
    public Flux<String> streamChat() {
        return chatControllerService.streamChat();
    }
}
