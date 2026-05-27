package se.iths.sara.authserver.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private final KeyPair keyPair = generateKeyPair();

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);

            return generator.generateKeyPair();

        } catch (Exception e) {
            throw new RuntimeException("Could not generate RSA key pair", e);
        }
    }

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .subject(username)
                .claim("roles", role)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + 1000 * 60 * 60
                        )
                )
                .signWith((RSAPrivateKey) keyPair.getPrivate())
                .compact();
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public Map<String, Object> getJwks() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        return Map.of(
                "keys", List.of(
                        Map.of(
                                "kty", "RSA",
                                "kid", "auth-server-key",
                                "use", "sig",
                                "alg", "RS256",
                                "n", Base64.getUrlEncoder().withoutPadding()
                                        .encodeToString(publicKey.getModulus().toByteArray()),
                                "e", Base64.getUrlEncoder().withoutPadding()
                                        .encodeToString(publicKey.getPublicExponent().toByteArray())
                        )
                )
        );
    }
}