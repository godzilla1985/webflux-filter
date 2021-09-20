package com.example.webfluxfilter.resolver;

import com.example.webfluxfilter.annotation.TestClass;
import com.example.webfluxfilter.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class HandlerEnrichFieldArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        log.info("Into {}" + this.getClass().getName());
        return methodParameter.getParameterType().equals(TestClass.class);
    }

    @SneakyThrows
    @Override
    public Mono<Object> resolveArgument(MethodParameter methodParameter,
                                        BindingContext bindingContext,
                                        ServerWebExchange serverWebExchange) {

        List<String> header = serverWebExchange.getRequest().getHeaders().get("xx-test-dto");
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
