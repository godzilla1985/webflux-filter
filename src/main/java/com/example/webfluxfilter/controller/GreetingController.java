package com.example.webfluxfilter.controller;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.service.GreetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class GreetingController {

    private final GreetingService greetingService;


    @GetMapping("greeting/{name}")
    public Mono<Enrichment> getName(@PathVariable String name,
                                    @Enrich(type = Type.TEST_DTO) TestDto testDto,
                                    @Enrich(type = Type.ENRICHED_GREETING_DTO)EnrichedGreetingDto enrichedGreetingDto) {
        return greetingService.getGreeting(name, testDto, enrichedGreetingDto);
    }


    @GetMapping("enrichments")
    public Flux<Enrichment> getAllEnrichments() {
        return greetingService.getAllEnrichments();
    }

}
