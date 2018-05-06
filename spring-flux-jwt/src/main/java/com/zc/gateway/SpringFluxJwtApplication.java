package com.zc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * A Spring RESTful Application showing authentication and authorization
 *
 */
@SpringBootApplication
@EnableWebFluxSecurity
public class SpringFluxJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFluxJwtApplication.class, args);
	}
}
