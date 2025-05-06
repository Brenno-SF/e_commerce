package com.bsf.e_commerce.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bsf.e_commerce.entity.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Client client){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(client.getUsername())
                .withClaim("clientId", client.getIdClient())
                .withClaim("nameClienet", client.getName_client())
                .withClaim()
    }
}
