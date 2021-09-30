package com.example.webfluxfilter.filter;

import com.example.webfluxfilter.dto.TestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Base64;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
public class AddBeanInContextFilter implements WebFilter {

    private static final String PREFIX_FOR_ADDING = "X-ENRICH";
    private static final String ENRICH_HEADER_PREFIX = "X-ENRICH-A";
    /*private static final String CONTEXT_MAP = "context-map";*/

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange ex, WebFilterChain chain) {
        return chain.filter(ex)
                .contextWrite(
                        ctx -> addRequestHeadersToContext(ex.getRequest(), ctx)
                );

    }

    private Context addRequestHeadersToContext(final ServerHttpRequest request, final Context context) {
        final Map<String, TestDto> contextMap = request
                .getHeaders().toSingleValueMap().entrySet()
                .stream()
                .filter(x -> x.getKey().startsWith(ENRICH_HEADER_PREFIX))
                .collect(toMap( Map.Entry::getKey, v -> getObjectFromJson(getJson(v.getValue()))));
                log.info(contextMap.toString());
        return context.put(ENRICH_HEADER_PREFIX,contextMap.get(ENRICH_HEADER_PREFIX));
    }

    @SneakyThrows
    private static TestDto getObjectFromJson(String json) {
        return objectMapper.readValue(json, TestDto.class);
    }

    private static String getJson(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString));
    }

}
