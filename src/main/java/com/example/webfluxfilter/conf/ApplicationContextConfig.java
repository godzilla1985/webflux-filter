package com.example.webfluxfilter.conf;

import com.example.webfluxfilter.filter.AddBeanInContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {


    @Bean
    public AddBeanInContextFilter addBeanInContextFilter() {
        return new AddBeanInContextFilter();
    }

}
