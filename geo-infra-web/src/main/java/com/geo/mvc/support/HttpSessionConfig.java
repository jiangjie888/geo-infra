package com.geo.mvc.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;

@Configuration  
@EnableRedisHttpSession   
public class HttpSessionConfig {  
     @Bean  
     public CookieHttpSessionStrategy cookieHttpSessionStrategy() {  
         CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();  
         strategy.setCookieSerializer(new CustomerCookiesSerializer());  
         return strategy;  
     }  
}  