package com.om1cael.sonora.api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtUtils {

    @Value("{jwt.secret}")
    private String secret;
    private final String issuer = "sonora-api";

    private final int HOURS_IN_SECONDS = 3600;

    public void createToken(String username) {
        JWT.create()
                .withSubject(username)
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(HOURS_IN_SECONDS * 24))
                .sign(getAlgorithm());
    }

    public String getSubject(String token) {
        JWTVerifier verification = JWT.require(getAlgorithm())
                .withIssuer(issuer)
                .build();

        return verification.verify(token).getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
