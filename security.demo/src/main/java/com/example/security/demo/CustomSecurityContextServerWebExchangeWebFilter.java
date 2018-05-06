package com.example.security.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.SecurityContextRepository;
import org.springframework.security.web.server.context.SecurityContextRepositoryServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

public class CustomSecurityContextServerWebExchangeWebFilter implements WebFilter {
    private static final Log LOGGER = LogFactory.getLog(CustomSecurityContextServerWebExchangeWebFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        LOGGER.error("custom CustomSecurityContextServerWebExchangeWebFilter -------------------------> " + exchange.getPrincipal().block());
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication((Authentication) exchange.getPrincipal().block());
        return chain.filter(new SecurityContextRepositoryServerWebExchange(exchange, (SecurityContextRepository) Mono.just(securityContext)));
	}
}
