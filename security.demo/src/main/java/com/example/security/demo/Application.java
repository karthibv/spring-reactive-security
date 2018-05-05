package com.example.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}

@Configuration
class WebConfiguration{
	
	
//	@Bean
//	RouterFunction<?> routes(){
//		return RouterFunctions.route(RequestPredicates.GET("/message"),new HandlerFunction<ServerResponse>() {
//
//			@Override
//			public Mono<ServerResponse> handle(ServerRequest serverRequest) {
//				Mono<String> principalPublisher= serverRequest.principal().map(p -> "Hello , " +p.getName() + "!");
//				return ServerResponse.ok().body(principalPublisher,String.class);
//			}
//			
//		});
//		
//	}
	
	
		private Mono<ServerResponse> message(ServerRequest serverRequest) {
			Mono<String> principalPublisher = serverRequest.principal().map(o -> "Hello , " + o.getName());
			return ServerResponse.ok().body(principalPublisher, String.class);
		}

	    private Mono<ServerResponse> username(ServerRequest serverRequest) {
	        Mono<UserDetails> detailsMono = serverRequest.principal()
	                .map(o -> UserDetails.class.cast(Authentication.class.cast(o).getPrincipal()));
	        return ServerResponse.ok().body(detailsMono, UserDetails.class);
	    }

		@Bean
	    RouterFunction<?> routes() {
			return route(GET("/hello"), this::message)
	                .andRoute(GET("/users/{username}"), this::username);
		}
		
}

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration{
	
	/*
	 * Authentication
	 */
	@Bean
	UserDetailsRepository userDetailsRepository() {
	    UserDetails karthik = User.withUsername("karthik").password("password").roles("USER").build();
	    UserDetails prathik = User.withUsername("prathik").password("password").roles("USER", "ADMIN").build();
	    return new MapUserDetailsRepository(karthik, prathik);
	}
	
	
	/*
	 * Authorization 
	 */
	@Bean
    SecurityWebFilterChain security(HttpSecurity httpSecurity) {
		return httpSecurity
				.authorizeExchange()
					.pathMatchers("/users/{username}").access(new ReactiveAuthorizationManager<AuthorizationContext>() {

						@Override
						public Mono<AuthorizationDecision> check(Mono<Authentication> authentication,
								AuthorizationContext context) {
							return authentication.map(auth -> auth.getName().equals(context.getVariables().get("username")))
									.map(AuthorizationDecision::new);
						}
					})
					.anyExchange().authenticated()
				.and()
				.build();
	}
	
	
	
}