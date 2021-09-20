package com.example.webfluxfilter.resolver;

import com.example.webfluxfilter.annotation.Enrich;
import com.example.webfluxfilter.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;


@AllArgsConstructor
@Slf4j
public class HandlerEnrichArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        log.info("Into supportsParameters method");
        log.info("Method parameter --> " + methodParameter.getMethod());
        return methodParameter.getParameterAnnotation(Enrich.class) != null;
    }

    @SneakyThrows
    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter,
                                        BindingContext bindingContext,
                                        ServerWebExchange serverWebExchange) {
        log.info("Into resolverArgument method");
        ServerHttpRequest request = serverWebExchange.getRequest();
        List<String> header = request.getHeaders().get("xx-test-dto");
        log.info("" + header);
        byte[] decodedBytes = null;
        String decodedString = null;
        if (header != null) {
            decodedBytes = Base64.getDecoder().decode(header.get(0));
        }
        if (decodedBytes != null) {
            decodedString = new String(decodedBytes);
        }
        TestDto testDto = new ObjectMapper().readValue(decodedString, TestDto.class);

        return Mono.just(testDto);
    }
}
