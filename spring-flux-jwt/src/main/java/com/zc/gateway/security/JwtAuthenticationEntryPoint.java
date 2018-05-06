package com.zc.gateway.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;
    
    

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
    	System.out.println("JwtAuthenticationEntryPoint >> >>>>>>>>"+serverWebExchange);
        return null;
    }
}