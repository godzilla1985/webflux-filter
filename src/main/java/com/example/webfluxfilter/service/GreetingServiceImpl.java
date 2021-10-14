package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {


    public Mono<Enrichment> getGreeting(String name, TestDto testDto, EnrichedGreetingDto enrichedGreetingDto) {
        log.info("The greeting message to {}", name);
        log.info(testDto.toString());
        log.info(enrichedGreetingDto.toString());
        return Mono.just(enrichedGreetingDto);
    }

}
