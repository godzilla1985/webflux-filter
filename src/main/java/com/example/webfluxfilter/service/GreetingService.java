package com.example.webfluxfilter.service;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDto;
import reactor.core.publisher.Mono;

public interface GreetingService {

    Mono<Enrichment> getGreeting(String name, TestDto testDto, EnrichedGreetingDto enrichedGreetingDto);


}
