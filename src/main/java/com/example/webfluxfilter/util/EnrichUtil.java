package com.example.webfluxfilter.util;

import com.example.webfluxfilter.dto.TestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class EnrichUtil {

    public Mono<TestDto> getEnrichAObject() {
        return Mono.subscriberContext()
                .map(x -> x.<Map<String, Object>>get("context-map"))
                .map(x -> x.get("X-ENRICH-A")).cast(TestDto.class);

    }

}
