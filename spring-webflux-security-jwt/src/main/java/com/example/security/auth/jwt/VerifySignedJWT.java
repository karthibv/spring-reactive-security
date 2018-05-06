
package com.example.security.auth.jwt;

import com.nimbusds.jwt.SignedJWT;
import reactor.core.publisher.Mono;
import java.text.ParseException;

public class VerifySignedJWT{
    public static Mono<SignedJWT> check(String token) {
        try {
            return Mono.just(SignedJWT.parse(token));
        } catch (ParseException e) {
          return Mono.empty();
        }
    }
}
