package com.orangescout.Orange.Scout.security;

import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = System.getenv("SECRET_KEY");

    @Autowired
    private UserRepository userRepository;

    // Extrai o username do token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrai a data de expiração do token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder() // Use parserBuilder() em vez de parser()
                .setSigningKey(getSecretKey()) // Use getSecretKey() aqui
                .build() // Construa o parser
                .parseClaimsJws(token) // Analise o token
                .getBody(); // Retorne o corpo das claims
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Gera um novo token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Adicione mais claims se necessário
        return createToken(claims, user.getEmail()); // Aqui você pode usar o email ou qualquer outro identificador
    }


    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token válido por 10 horas
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // Use a chave secreta aqui
                .compact();
    }


    public boolean validateToken(String token, MyUserDetails userDetails) {
        try {
            // Verifica a assinatura e a expiração
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey()) // A mesma chave usada para assinar
                    .build()
                    .parseClaimsJws(token);

            // Extraia o nome do usuário do token
            String extractedUsername = claims.getBody().getSubject();
            System.out.println("Subject:" +extractedUsername);

            // Verifique se o nome extraído corresponde ao nome fornecido
            if (!extractedUsername.equals(userDetails.getEmail())) {
                System.out.println("A requisição veio até aqui ("+extractedUsername + ", "+userDetails.getEmail()+")");
                return false; // O nome não corresponde
            }

            System.out.println("Chegou ate o return userRepository " + extractedUsername);
            // Verifique se o usuário existe no banco de dados
            return userRepository.existsByEmail(extractedUsername); // Supondo que você tenha esse método

        } catch (JwtException e) {
            // Log do erro
            System.out.println("Invalid token: " + e.getMessage());
            return false;
        }
    }



    private SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
}
