package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.TestDto;
import reactor.core.publisher.Mono;

public interface GreetingService {

    Mono<TestDto> getGreeting(String name, TestDto testDto);


}
