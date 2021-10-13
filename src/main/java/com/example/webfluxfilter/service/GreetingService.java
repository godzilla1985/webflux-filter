package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDtoA;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface GreetingService {

    Mono<TestDtoA> getGreeting(String name);

    Mono<EnrichedGreetingDto> getEnrichedGreetingDto(String name);

    Flux<Enrichment> getAllEnrichments();

}
