package com.example.webfluxfilter.annotation;

import com.example.webfluxfilter.constant.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Enrich {
    Type type() default Type.EMPTY;
}
