package com.medicare_backend.medicare_backend.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.medicare_backend.medicare_backend.schema.entity.User;

public class TokenAuthenticationService {
    public static String generateJWTToken(String requestUser) throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("string-claim", requestUser)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            System.out.println(e);
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return "Error";
    }

    public static String verifyJWTToken(String tokenString) throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("string-claim", "username")
                    .withClaimPresence("some-claim-that-just-needs-to-be-present")
                    .withClaim("predicate-claim", (claim, decodedJWT) -> "custom value".equals(claim.asString()))
                    .build();
            DecodedJWT jwt = verifier.verify(tokenString);
            String customStringClaim = jwt.getClaim("custom-string-claim").asString();
            return customStringClaim;
        } catch (JWTVerificationException e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return "";
    }

}