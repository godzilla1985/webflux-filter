package com.example.webfluxfilter.annotation;

import com.example.webfluxfilter.constant.Type;
import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDtoA;
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
import java.util.List;
import java.util.Map;

@Slf4j
public class EnrichAnnotationMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Map<Type, Pair<String, Class<?>>> MAP_TYPE_TO_PAIR = Map.ofEntries(
            Map.entry(Type.TEST_DTO, new Pair<>("X-ENRICH-A", TestDtoA.class)),
            Map.entry(Type.ENRICHED_GREETING_DTO, new Pair<>("X-ENRICH-B", EnrichedGreetingDto.class)));


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
        Type type = methodParameter.getParameterAnnotation(Enrich.class).type();
        List<String> headerContent = serverHttpRequest.getHeaders().get(MAP_TYPE_TO_PAIR.get(type).getValue0());
        if (headerContent == null) {
            throw new RuntimeException("The value of the header " + MAP_TYPE_TO_PAIR.get(type).getValue0() + " is empty." +
                    "Please care about that the value is filled.");
        }
        String header = headerContent.get(0);
        Enrichment enrichment = getObjectFromJson(header, type);
        return Mono.just(enrichment);
    }

    @SneakyThrows
    private Enrichment getObjectFromJson(String json, Type type) {
        byte[] decodedBytes = Base64.getDecoder().decode(json);
        return (Enrichment) objectMapper.readValue(new String(decodedBytes), MAP_TYPE_TO_PAIR.get(type).getValue1());
    }

}
