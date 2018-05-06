# spring-reactive-security

## 1) Maven Setup ##
        <dependency>
		    	<groupId>org.springframework.boot</groupId>
			    <artifactId>spring-boot-starter-webflux</artifactId>
	  	  </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-core</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-webflux</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
        </dependency>


## 2) Spring Security Configuration Class

To enable WebFlux support in Spring Security 5, we only need to specify the @EnableWebFluxSecurity annotation:
@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration{

## 3) Authenticaiton with in-memory store
we’ll need a user details service. Spring Security provides us with a convenient mock user builder and an in-memory implementation of the user details service:

@Bean
	UserDetailsRepository userDetailsRepository() {
	    UserDetails karthik = User.withUsername("karthik").password("password").roles("USER").build();
	    UserDetails prathik = User.withUsername("prathik").password("password").roles("USER", "ADMIN").build();
	    return new MapUserDetailsRepository(karthik, prathik);
	}
  
## 4) Authorization

Now we can take advantage of the class ServerHttpSecurity to build our security configuration.

This class is a new feature of Spring 5. It’s similar to HttpSecurity builder, but it’s only enabled for WebFlux applications.

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
  
  ## 5) Reactive Controller Security


     @Bean
	    RouterFunction<?> routes() {
			return route(GET("/hello"), this::message)
	                .andRoute(GET("/users/{username}"), this::username);
		 }



### Test output: ###

 curl -ukarthik:password -v http://localhost:8080/hello
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
* Server auth using Basic with user 'karthik'
> GET /hello HTTP/1.1
> Host: localhost:8080
> Authorization: Basic a2FydGhpazpwYXNzd29yZA==
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: text/plain;charset=UTF-8
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Content-Type-Options: nosniff
< X-Frame-Options: DENY
< X-XSS-Protection: 1 ; mode=block


 curl -ukarthik:password -v http://localhost:8080/users/karthik
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
* Server auth using Basic with user 'karthik'
> GET /users/karthik HTTP/1.1
> Host: localhost:8080
> Authorization: Basic a2FydGhpazpwYXNzd29yZA==
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< transfer-encoding: chunked
< Content-Type: application/json;charset=UTF-8
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Content-Type-Options: nosniff
< X-Frame-Options: DENY
< X-XSS-Protection: 1 ; mode=block
< 
* Connection #0 to host localhost left intact
{"password":"password","username":"karthik","authorities":[{"authority":"ROLE_USER"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true,"name":"karthik"}




