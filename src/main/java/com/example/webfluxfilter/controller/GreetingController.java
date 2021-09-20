package com.example.webfluxfilter.controller;

import com.example.webfluxfilter.annotation.Enrich;
import com.example.webfluxfilter.annotation.TestClass;
import com.example.webfluxfilter.dto.TestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
@Slf4j
public class GreetingController {
    @TestClass
    TestDto sample;

    @GetMapping("greeting/{name}")
    public Mono<Object> getName(@PathVariable String name, @Enrich TestDto testDto) {
        log.info("" + testDto);
        log.info("sample --> "+sample);
        return Mono.just("Shalom " + name + "!");
    }

}
