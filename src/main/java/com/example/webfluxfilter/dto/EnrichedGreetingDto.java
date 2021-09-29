package com.example.webfluxfilter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrichedGreetingDto {

    private String name;
    private TestDto enrichedObject;

}
