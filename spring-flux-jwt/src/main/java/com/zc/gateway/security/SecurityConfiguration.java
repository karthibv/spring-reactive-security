package com.zc.gateway.security;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import com.zc.gateway.security.service.CustomReactiveUserDetailsService;


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    private ServerAuthenticationEntryPoint entryPoint = new JwtAuthenticationEntryPoint();

    private final CustomReactiveUserDetailsService userDetailsService;
    private final CustomAuthenticationConverter customAuthenticationConverter;

    
    public SecurityConfiguration(CustomReactiveUserDetailsService userDetailsService, CustomAuthenticationConverter customAuthenticationConverter) {
        this.userDetailsService = userDetailsService;
        this.customAuthenticationConverter = customAuthenticationConverter;
    }
    
    
    private ReactiveAuthenticationManager authenticationManager() {
        CustomReactiveAuthenticationManager customReactiveAuthenticationManager =  new CustomReactiveAuthenticationManager(this.userDetailsService);
        return customReactiveAuthenticationManager;
    }
    
    private AuthenticationWebFilter apiAuthenticationWebFilter() {
        try {
            AuthenticationWebFilter apiAuthenticationWebFilter = new AuthenticationWebFilter(authenticationManager());
            apiAuthenticationWebFilter.setServerAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(this.entryPoint));
            apiAuthenticationWebFilter.setAuthenticationConverter(this.customAuthenticationConverter);
            apiAuthenticationWebFilter.setRequiresAuthenticationMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"));

            apiAuthenticationWebFilter.setServerSecurityContextRepository(securityContextRepository());

            return apiAuthenticationWebFilter;
        } catch (Exception e) {
            throw new BeanInitializationException("Could not initialize AuthenticationWebFilter apiAuthenticationWebFilter.", e);
        }
    }

    @Bean
    public WebSessionServerSecurityContextRepository securityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new CustomPasswordEncoder();
    }
    
    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        // Disable default security.
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().disable();
        http.logout().disable();

        // Add custom security.
        http.authenticationManager(authenticationManager());

        // Disable authentication for `/auth/**` routes.
       // http.authorizeExchange().pathMatchers("/v1/api/auth/**").permitAll();
        http.authorizeExchange().pathMatchers("/auth/**").permitAll();

        http.securityContextRepository(securityContextRepository());

        http.authorizeExchange().anyExchange().authenticated();
        //.and().httpBasic().disable();

        http.addFilterAt(apiAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        // .httpBasic().disable().csrf().disable();

        return http.build();
    }
}
