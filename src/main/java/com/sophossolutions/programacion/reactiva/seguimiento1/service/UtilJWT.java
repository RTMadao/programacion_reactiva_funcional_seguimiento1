package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UtilJWT {

    @Value("${jwt_secret}")
    private String secret;
    private static final String SUBJECT = "User Details";
    private static final String ISSUER = "CURSO_PROGRAMACION_REACTIVA";
    private static final String USER_CLAIM = "username";
    private static final String PASSWORD_CLAIM = "password";

    public String generateToken(UserDetails userDetails) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim(USER_CLAIM, userDetails.getUsername())
                .withClaim(PASSWORD_CLAIM, userDetails.getPassword())
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(secret));
    }

    public User validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return User.builder()
                .username(jwt.getClaim(USER_CLAIM).asString())
                .password(jwt.getClaim(PASSWORD_CLAIM).asString())
                .build();
    }

}