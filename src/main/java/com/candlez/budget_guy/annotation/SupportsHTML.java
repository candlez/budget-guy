package com.candlez.budget_guy.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this is only meant to be used on error handlers in a controller advice
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportsHTML {
    HttpStatus value();
    String destination() default "";
}
