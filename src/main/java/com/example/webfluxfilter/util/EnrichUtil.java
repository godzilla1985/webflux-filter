package com.example.webfluxfilter.util;

import com.example.webfluxfilter.dto.TestDtoA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.Map;

@Component
@Slf4j
public class EnrichUtil {

    public Mono<TestDtoA> getEnrichAObject() {
        return Mono.subscriberContext()
                .map(x -> x.<Map<String, Object>>get("context-map"))
                .map(x -> x.get("X-ENRICH-A")).cast(TestDtoA.class);

    }

    public TestDtoA getTestDtoFromContext(ContextView contextView){
        return contextView.get("X-ENRICH-A");
    }

}
