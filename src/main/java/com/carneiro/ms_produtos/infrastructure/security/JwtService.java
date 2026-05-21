package com.carneiro.ms_produtos.infrastructure.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    // Converte a secret em chave criptográfica
    private SecretKey getSigningKey() {

        byte[] keyBytes =
                secretKey.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Gera token JWT
    public String generateToken(String username) {

        return Jwts.builder()

                // usuário dono do token
                .subject(username)

                // data de criação
                .issuedAt(new Date())

                // expiração (1 hora)
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )

                // assinatura criptográfica
                .signWith(getSigningKey())

                // transforma em String JWT
                .compact();
    }

    // Extrai username do token
    public String extractUsername(String token) {

        return Jwts.parser()

                // valida assinatura
                .verifyWith(getSigningKey())

                // constrói parser
                .build()

                // lê token
                .parseSignedClaims(token)

                // pega payload
                .getPayload()

                // pega subject(username)
                .getSubject();
    }

    // Verifica se token é válido
    public boolean isTokenValid(String token) {

        try {

            Jwts.parser()

                    .verifyWith(getSigningKey())

                    .build()

                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}