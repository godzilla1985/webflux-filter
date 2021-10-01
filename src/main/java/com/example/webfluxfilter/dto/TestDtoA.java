package com.example.webfluxfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDtoA implements Enrichment{

    private boolean status;
    private String name;
    private String data;

}
