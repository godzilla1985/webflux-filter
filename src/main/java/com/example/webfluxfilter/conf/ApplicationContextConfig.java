package com.example.webfluxfilter.conf;

import com.example.webfluxfilter.filter.AddBeanInContextFilter;
import com.example.webfluxfilter.filter.HelloWorldFilter;
import com.example.webfluxfilter.filter.PrintHeaderFilter;
import com.example.webfluxfilter.resolver.HandlerEnrichArgumentResolver;
import com.example.webfluxfilter.resolver.HandlerEnrichFieldArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public HelloWorldFilter helloWorldFilter() {
        return new HelloWorldFilter();
    }

    @Bean
    public PrintHeaderFilter printHeaderFilter() {
        return new PrintHeaderFilter();
    }

    @Bean
    public AddBeanInContextFilter addBeanInContextFilter() {
        return new AddBeanInContextFilter();
    }

    @Bean
    public WebFluxConfigurer enrichHeadersConfigurerResolver() {
        return new WebFluxConfigurer() {
            @Override
            public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
                configurer.addCustomResolver(new HandlerEnrichArgumentResolver(), new HandlerEnrichFieldArgumentResolver());
            }
        };
    }


}
