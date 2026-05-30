package com.svb.spring_ai.service;

import reactor.core.publisher.Flux;

public interface ChatControllerService {
    public String chat();

    public Flux<String> streamChat();
}
