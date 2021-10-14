package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.TestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {


    public Mono<TestDto> getGreeting(String name, TestDto testDto) {
        log.info("The greeting message to {}", name);
//        return Mono.just(new TestDto(true, name, LocalDateTime.now().toString()));
        return Mono.just(testDto);
    }

}
