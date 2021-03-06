package com.example.webfluxfilter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrichedGreetingDto implements Enrichment{

    private String name;
    private TestDtoA enrichedObject;

}
