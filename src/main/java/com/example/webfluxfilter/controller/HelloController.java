package com.example.webfluxfilter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api")
@Slf4j
public class HelloController {

    private static final Collector<CharSequence, ?, String> COLLECTOR
            = Collectors.joining("," + System.lineSeparator(), "[", "]");


    @GetMapping("users/{name}")
    public Mono<Object> getName(@PathVariable String name) {
        return Mono.just("The context contains: " + System.lineSeparator())
                .flatMap(HelloController::apply);
    }

    private static Mono<Object> apply(String prefix) {
//        return Mono.subscriberContext()
//                .map(x -> x.<Map<String, String>>get("context-map"))
//                .map(x -> prefix + x.entrySet().stream()
//                        .map(kv -> kv.getKey() + ": " + kv.getValue())
//                        .collect(COLLECTOR));
        return Mono.subscriberContext()
                .map(x -> x.<Map<String, Object>>get("Sidecar"));
    }

}
