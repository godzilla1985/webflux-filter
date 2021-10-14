package com.example.webfluxfilter.annotation;

import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EnrichAnnotationMethodArgumentResolver implements HandlerMethodArgumentResolver {

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
        Map<String, String> headers = serverHttpRequest.getHeaders().toSingleValueMap().entrySet().stream()
                .filter(x -> x.getKey().startsWith("X-ENRICH"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("This is headers {}",headers);
        Enrichment enrichment = getObjectFromJson(getJson(headers.get("X-ENRICH-A")));
        return Mono.just(enrichment);
    }

    @SneakyThrows
    private Enrichment getObjectFromJson(String json){
        return objectMapper.readValue(json, TestDto.class);
    }

    private static String getJson(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return  new String(decodedBytes);
    }
}
