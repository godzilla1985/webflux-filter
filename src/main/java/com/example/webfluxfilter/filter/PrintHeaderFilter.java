package com.example.webfluxfilter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Slf4j
public class PrintHeaderFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
        log.info("This is headers of the request : " + headers);
        return webFilterChain.filter(serverWebExchange);
    }
}
