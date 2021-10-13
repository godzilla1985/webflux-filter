package com.example.webfluxfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDtoC implements Enrichment{

    private String exceptionName;
    private boolean disable;

}
