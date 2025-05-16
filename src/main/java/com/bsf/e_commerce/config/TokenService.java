package com.bsf.e_commerce.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bsf.e_commerce.entity.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Client client){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(client.getUsername())
                .withClaim("clientId", client.getIdClient())
                .withClaim("name", client.getName_client())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API E-commerce")
                .sign(algorithm);
    }

    public Optional<JWTClientData> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);


            return Optional.of(
                    JWTClientData
                        .builder()
                        .id(jwt.getClaim("clientId").asString())
                        .name(jwt.getClaim("name").asString())
                        .username(jwt.getSubject())
                        .build());

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }


    }
}
