package com.geo.mvc.support.filter;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface Auth {  
    String value() default "";  
    String name() default "";  
}