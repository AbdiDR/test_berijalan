package com.example.demo.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTGenerator {

    private static final Logger log = LoggerFactory.getLogger(JWTGenerator.class);

    @Value("${jwt.secret}")
    private String secretKey;

    // Method untuk membuat JWT token
    public String createJWT(String id, String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Gunakan Base64 untuk encoding secret key
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(secretKey);
        SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer("Admin")
                .setAudience("PesertaMagang")
                .signWith(signingKey, signatureAlgorithm);

        long expMillis = nowMillis + 2628000000L;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        return builder.compact();
    }

    // Method untuk decode JWT dan mengambil claims
    public Claims decodeJWT(String jwt) {
        // Gunakan Base64 untuk decoding secret key
        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .parseClaimsJws(jwt)
                .getBody();

        log.info("ID : {}", claims.getId());
        log.info("Issuer : {}", claims.getIssuer());
        log.info("Subject : {}", claims.getSubject());

        return claims;
    }
}
