package com.example.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import com.example.security.auth.JWTAuthorizationWebFilter;
import com.example.security.auth.WebFilterChainServerJWTAuthenticationSuccessHandler;

/**
 * A Spring RESTful Application showing authentication and authorization
 *
 */
@SpringBootApplication
@EnableWebFluxSecurity
public class FluxJwtSecureApplication {

    private static final Log LOGGER = LogFactory.getLog(FluxJwtSecureApplication.class);

    /**
     * Main entry point, built on top of Spring Boot it will point the begin of
     * execution.
     *
     * @param args Regular command line arguments can be added and their treatment
     *             may be required
     */
    public static void main(String[] args) {
        SpringApplication.run(FluxJwtSecureApplication.class, args);
    }

    
    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("karthik@gmail.com")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    
    
    
    
    /**
     * For Spring Security webflux, a chain of filters will provide user authentication
     * and authorization, we add custom filters to enable JWT token approach.
     *
     * @param http An initial object to build common filter scenarios.
     *             Customized filters are added here.
     * @return SecurityWebFilterChain A filter chain for web exchanges that will
     * provide security
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    	
    	LOGGER.debug("FLuxJwt - springSecurityFilterChain");
        AuthenticationWebFilter authenticationJWT;

        authenticationJWT = new AuthenticationWebFilter(new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository()));
        authenticationJWT.setServerAuthenticationSuccessHandler(new WebFilterChainServerJWTAuthenticationSuccessHandler());

        http
                .authorizeExchange()
                .pathMatchers("/login", "/").permitAll()  // permit the resource with out authentication
                .and()
                .addFilterAt(authenticationJWT, SecurityWebFiltersOrder.FIRST)
                .authorizeExchange()
                .pathMatchers("/api/**")
                .authenticated()
                .and()
                .addFilterAt(new JWTAuthorizationWebFilter(), SecurityWebFiltersOrder.HTTP_BASIC);
        LOGGER.debug("FLuxJwt - springSecurityFilterChain");

        return http.build();
    }
}
