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
import java.util.Map;

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
        Map<String, Enrichment> enrichmentMap = contextView.get("ENRICHMENTS-MAP");
        Collection<Enrichment> enrichmentList = enrichmentMap.values();
        return Flux.fromIterable(enrichmentList);
    }

    private Mono<TestDtoA> getBeanTestDtoFromContext(ContextView contextView) {
        Map<String, Enrichment> enrichmentMap = contextView.get("ENRICHMENTS-MAP");
        TestDtoA testDtoA = (TestDtoA) enrichmentMap.get("X-ENRICH-A");
        return Mono.just(testDtoA);
    }

    private Mono<EnrichedGreetingDto> getBeanFromContext(ContextView contextView, String name) {
        Map<String, Enrichment> enrichmentMap = contextView.get("ENRICHMENTS-MAP");
        return Mono.just(new EnrichedGreetingDto(name, (TestDtoA) enrichmentMap.get("X-ENRICH-A")));
    }


}
