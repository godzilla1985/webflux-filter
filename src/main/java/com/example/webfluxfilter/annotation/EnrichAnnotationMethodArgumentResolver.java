package com.example.webfluxfilter.annotation;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Map;

@Slf4j
public class EnrichAnnotationMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Map<String, Pair<String,Class<?>>> MAP_TYPE_TO_PAIR = Map.ofEntries(
            Map.entry("test-a",new Pair<>("X-ENRICH-A",TestDto.class)),
            Map.entry("test-b",new Pair<>("X-ENRICH-B",EnrichedGreetingDto.class)));


    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(Enrich.class) != null;
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter,
                                        BindingContext bindingContext,
                                        ServerWebExchange serverWebExchange) {

        ServerHttpRequest serverHttpRequest = serverWebExchange.getRequest();
        String type = methodParameter.getParameterAnnotation(Enrich.class).type();
        String header = serverHttpRequest.getHeaders().get(MAP_TYPE_TO_PAIR.get(type).getValue0()).get(0);
        Enrichment enrichment = getObjectFromJson(header,type);
        return Mono.just(enrichment);
    }

    @SneakyThrows
    private Enrichment getObjectFromJson(String json, String type) {
        byte[] decodedBytes = Base64.getDecoder().decode(json);
        return (Enrichment) objectMapper.readValue(new String(decodedBytes), MAP_TYPE_TO_PAIR.get(type).getValue1());
    }

}
