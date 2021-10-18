package com.example.webfluxfilter.config;

import com.example.webfluxfilter.annotation.EnrichAnnotationMethodArgumentResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@ConditionalOnProperty(name = "enrichment.databinding.enabled",havingValue = "true")
public class WebConfig implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new EnrichAnnotationMethodArgumentResolver());
    }
}
