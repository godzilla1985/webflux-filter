package com.example.webfluxfilter;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.TestDto;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
public class ReactorContextInteractionTests {


    @Test
    void mergeTwoDifferentMono() {}

    @Test
    void buildMapWithPairTesting(){
        Map<String,Pair<String,Class<?>>> map = Map.ofEntries(
                Map.entry("test-a",new Pair<>("X-ENRICH-A",TestDto.class)),
                Map.entry("test-b",new Pair<>("X-ENRICH-B",EnrichedGreetingDto.class)));
        Pair<String,Class<?>> item = map.get("test-a");
        log.info(item.toString());
        log.info(item.getValue0());
        log.info(item.getValue1().toString());
    }

}
