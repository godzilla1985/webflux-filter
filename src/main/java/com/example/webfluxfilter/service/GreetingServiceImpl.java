package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.TestDto;
import com.example.webfluxfilter.util.EnrichUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

@Service
@Slf4j
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {

    private final EnrichUtil enrichUtil;

    public Mono<TestDto> getGreeting(String name) {
        log.info("The greeting message to {}", name);
        return enrichUtil.getEnrichAObject();
    }

    public Mono<EnrichedGreetingDto> getEnrichedGreetingDto(String name) {
        return Mono.deferContextual(ctx-> getBeanFromContext(ctx,name));
    }

    private Mono<EnrichedGreetingDto> getBeanFromContext(ContextView contextView, String name) {
        TestDto testDto = contextView.get("X-ENRICH-A");
        return Mono.just(new EnrichedGreetingDto(name,testDto));
    }
}
