package com.example.webfluxfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDtoB implements Enrichment{

    private String message;
    private int age;

}
