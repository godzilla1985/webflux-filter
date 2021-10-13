package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDtoA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GreetingServiceImpl implements GreetingService {


    public Mono<TestDtoA> getGreeting(String name) {
        log.info("The greeting message to {}", name);
        return Mono.deferContextual(this::getBeanTestDtoFromContext);
    }

    public Mono<EnrichedGreetingDto> getEnrichedGreetingDto(String name) {
        return Mono.deferContextual(ctx -> getBeanFromContext(ctx, name));
    }

    public Flux<Enrichment> getAllEnrichments() {
        return Flux.deferContextual(this::getBeanFromContext);
    }


    private Flux<Enrichment> getBeanFromContext(ContextView contextView) {
        Collection<Enrichment> enrichmentList = contextView.stream().map(entry -> ((Enrichment) entry.getValue())).collect(Collectors.toList());
        return Flux.fromIterable(enrichmentList);
    }

    private Mono<TestDtoA> getBeanTestDtoFromContext(ContextView contextView) {
        TestDtoA testDtoA = contextView.get("X-ENRICH-A");
        return Mono.just(testDtoA);
    }

    private Mono<EnrichedGreetingDto> getBeanFromContext(ContextView contextView, String name) {
        return Mono.just(new EnrichedGreetingDto(name, contextView.get("X-ENRICH-A")));
    }


}
