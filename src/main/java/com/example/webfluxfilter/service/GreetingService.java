package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDtoA;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GreetingService {

    Mono<Enrichment> getGreetingThroughDataBinding(String name, TestDtoA testDto, EnrichedGreetingDto enrichedGreetingDto);


    Flux<Enrichment> getAllEnrichmentsThroughWebFilterReactorContext();

}
