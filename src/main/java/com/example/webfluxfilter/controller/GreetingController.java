package com.example.webfluxfilter.controller;

import com.example.webfluxfilter.service.GreetingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@AllArgsConstructor
@Slf4j
public class GreetingController {

    private final GreetingService greetingService;

    @GetMapping("greeting/{name}")
    public Mono<Object> getName(@PathVariable String name) {
        return greetingService.getGreeting(name);
    }

}
