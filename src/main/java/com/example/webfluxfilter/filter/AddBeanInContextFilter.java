package com.example.webfluxfilter.filter;

import com.example.webfluxfilter.beanpojo.Sidecar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
public class AddBeanInContextFilter implements WebFilter {

    public static final String CONTEXT_MAP = "context-map";
    public static final String SIDECAR_HEADER_PREFIX = "Sidecar";


    @Override
    public Mono<Void> filter(ServerWebExchange ex, WebFilterChain chain) {

        return chain.filter(ex)
                .subscriberContext(
                        ctx -> addRequestHeadersToContext(ex.getRequest(), ctx)
                );
    }

    private Context addRequestHeadersToContext(final ServerHttpRequest request, final Context context) {
        Sidecar sidecar = new Sidecar();
        final Map<String, String> contextMap = request
                .getHeaders().toSingleValueMap().entrySet()
                .stream()
                .filter(x -> x.getKey().startsWith(SIDECAR_HEADER_PREFIX))
                .collect(
                        toMap(v -> v.getKey().substring(SIDECAR_HEADER_PREFIX.length()),
                                Map.Entry::getValue
                        )
                );
        sidecar.setName(contextMap.get(SIDECAR_HEADER_PREFIX));
        log.info(contextMap.toString());
        log.info(sidecar.toString());
        return context.put(SIDECAR_HEADER_PREFIX, sidecar);
        /*final Map<String, String> contextMap = request
                .getHeaders().toSingleValueMap().entrySet()
                .stream()
                .filter(x -> x.getKey().startsWith(SIDECAR_HEADER_PREFIX))
                .collect(
                        toMap(v -> v.getKey().substring(SIDECAR_HEADER_PREFIX.length()),
                                Map.Entry::getValue
                        )
                );
        return context.put(CONTEXT_MAP, contextMap);*/
    }

}
