package com.example.webfluxfilter;

import com.example.webfluxfilter.dto.TestMapToObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class MapToObjectTest {

    private static final Map<String, String> DICTIONARY = Map.ofEntries(
            Map.entry("enrichA", "enrichAContent"),
            Map.entry("enrichB", "enrichBContent"),
            Map.entry("enrichC", "enrichCContent"),
            Map.entry("enrichD", "enrichDContent"));

    private static final Map<String, String> DICTIONARY_HAVE_NOT_DATA = Map.ofEntries(
            Map.entry("enrichA", "enrichAContent"),
            Map.entry("enrichD", "enrichDContent"));

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void mapToObjectTest() {
        TestMapToObject testMapToObject = objectMapper.convertValue(DICTIONARY,TestMapToObject.class);
        log.info(testMapToObject.toString());
    }

    @Test
    public void mapToObjectTest2() {
        TestMapToObject testMapToObject = objectMapper.convertValue(DICTIONARY_HAVE_NOT_DATA,TestMapToObject.class);
        log.info(testMapToObject.toString());
    }

    @Test
    public void changeTheMapKeys(){
        TestMapToObject testMapToObject = new TestMapToObject();
        List<String> listOfFields = Arrays.stream(testMapToObject.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
        System.out.println(listOfFields);
    }

    @Test
    public void putIfAbsentTest(){
    }

}
