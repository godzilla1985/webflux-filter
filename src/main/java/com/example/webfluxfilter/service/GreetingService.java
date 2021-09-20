package com.example.webfluxfilter.service;

import reactor.core.publisher.Mono;

public interface GreetingService {

    Mono<Object> getGreeting(String name);

}
