package com.example.webfluxfilter.filter;

import com.example.webfluxfilter.dto.Enrichment;
import com.example.webfluxfilter.dto.TestDtoA;
import com.example.webfluxfilter.dto.TestDtoB;
import com.example.webfluxfilter.dto.TestDtoC;
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

    private static final Map<String, Class<?>> DICTIONARY = Map.ofEntries(
            Map.entry("X-ENRICH-A", TestDtoA.class),
            Map.entry("X-ENRICH-B", TestDtoB.class),
            Map.entry("X-ENRICH-C", TestDtoC.class));

    private static final String ENRICHMENTS_MAP = "ENRICHMENTS-MAP";

    private static final String PREFIX_FOR_ADDING = "X-ENRICH";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange ex, WebFilterChain chain) {
        return chain.filter(ex)
                .contextWrite(
                        ctx -> addRequestHeadersToContext(ex.getRequest(), ctx)
                );
    }

    private Context addRequestHeadersToContext(final ServerHttpRequest request, final Context context) {
        final Map<String, Enrichment> contextMap = request
                .getHeaders().toSingleValueMap().entrySet()
                .stream()
                .filter(x -> x.getKey().startsWith(PREFIX_FOR_ADDING))
                .collect(toMap(Map.Entry::getKey, entry -> getObjectFromJson(getJson(entry.getValue()), entry.getKey())));
        log.info(contextMap.toString());
        return context.put(ENRICHMENTS_MAP, contextMap);
    }

    @SneakyThrows
    private static Enrichment getObjectFromJson(String json, String HeaderName) {
        return (Enrichment) objectMapper.readValue(json, DICTIONARY.get(HeaderName));
    }

    private static String getJson(String encodedString) {
        return new String(Base64.getDecoder().decode(encodedString));
    }

}
