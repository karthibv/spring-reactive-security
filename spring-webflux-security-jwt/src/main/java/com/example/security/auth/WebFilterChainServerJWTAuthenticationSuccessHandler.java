
package com.example.security.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * On success authentication a signed JWT object is serialized and added
 * in the authorization header as a bearer token
 */
public class WebFilterChainServerJWTAuthenticationSuccessHandler
        implements ServerAuthenticationSuccessHandler {

    /**
     * A successful authentication object us used to create a JWT object and
     * added in the authorization header of the current WebExchange
     *
     * @param webFilterExchange
     * @param authentication
     * @return
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
    	System.out.println("JWTReactiveAuthenticationManager >>>onAuthenticationSuccess "+webFilterExchange);

    	ServerWebExchange exchange = webFilterExchange.getExchange();
        //TODO refactor this nasty implementation
        exchange.getResponse()
                .getHeaders()
                .add(HttpHeaders.AUTHORIZATION, getHttpAuthHeaderValue(authentication));
        return webFilterExchange.getChain().filter(exchange);
    }

    private static String getHttpAuthHeaderValue(Authentication authentication){
    	System.out.println("JWTReactiveAuthenticationManager >>>getHttpAuthHeaderValue "+authentication);

        return String.join(" ","Bearer",tokenFromAuthentication(authentication));
    }

    private static String tokenFromAuthentication(Authentication authentication){
    	System.out.println("JWTReactiveAuthenticationManager >>>tokenFromAuthentication "+authentication);

        return new JWTTokenService().generateToken(
                                            authentication.getName(),
                                            authentication.getCredentials(),
                                            authentication.getAuthorities());
    }
}
