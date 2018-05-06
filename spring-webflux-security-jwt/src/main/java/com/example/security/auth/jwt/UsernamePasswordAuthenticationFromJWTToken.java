
package com.example.security.auth.jwt;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsernamePasswordAuthenticationFromJWTToken  {

    public static Authentication create(Mono<SignedJWT> signedJWTMono) {
        SignedJWT signedJWT = signedJWTMono.block();
        String subject;
        String auths;
        List authorities;

        try {
            subject = signedJWT.getJWTClaimsSet().getSubject();
            auths = (String) signedJWT.getJWTClaimsSet().getClaim("auths");
        } catch (ParseException e) {
            return null;
        }
        authorities = Stream.of(auths.split(","))
                .map(a -> new SimpleGrantedAuthority(a))
                .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(subject, null, authorities);

    }
}
