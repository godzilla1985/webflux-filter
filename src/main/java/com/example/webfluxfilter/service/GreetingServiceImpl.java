package com.example.webfluxfilter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class GreetingServiceImpl implements GreetingService {


    @Override
    public Mono<Object> getGreeting(String name) {
        return Mono.subscriberContext()
                .map(x -> x.<Map<String, Object>>get("context-map"))
                .map(x -> x.get("X-ENRICH-A"));
    }


}
