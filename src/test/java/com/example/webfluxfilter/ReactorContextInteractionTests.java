package com.example.webfluxfilter;

import com.example.webfluxfilter.dto.EnrichedGreetingDto;
import com.example.webfluxfilter.dto.TestDtoA;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class ReactorContextInteractionTests {


    @Test
    void mergeTwoDifferentMono() {
    }

    @Test
    void buildMapWithPairTesting() {
        Map<String, Pair<String, Class<?>>> map = Map.ofEntries(
                Map.entry("test-a", new Pair<>("X-ENRICH-A", TestDtoA.class)),
                Map.entry("test-b", new Pair<>("X-ENRICH-B", EnrichedGreetingDto.class)));
        Pair<String, Class<?>> item = map.get("test-a");
        log.info(item.toString());
        log.info(item.getValue0());
        log.info(item.getValue1().toString());
    }

}
