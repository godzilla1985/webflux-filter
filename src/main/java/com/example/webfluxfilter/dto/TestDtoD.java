package com.example.webfluxfilter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDtoD implements Enrichment {

    ArrayList<TestDtoB> messages = new ArrayList<>();

}
