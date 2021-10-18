package com.example.webfluxfilter.config;

import com.example.webfluxfilter.filter.AddBeanInContextFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "enrichment.databinding.enabled",havingValue = "false")
public class WebFluxFilterConfig {

    @Bean
    public AddBeanInContextFilter addBeanInContextFilter() {
        return new AddBeanInContextFilter();
    }

}
