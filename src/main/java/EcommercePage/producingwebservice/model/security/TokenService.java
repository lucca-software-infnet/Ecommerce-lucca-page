package EcommercePage.producingwebservice.model.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import EcommercePage.producingwebservice.model.domain.Solicitante;

@Service
public class TokenService {

    private String secret = "segredinho";

    public String generateToken(Solicitante solicitante) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("auth")
                .withSubject(solicitante.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm)
                .withIssuer("auth")
                .build();
            var decodedJWT = verifier.verify(token);
            String subject = decodedJWT.getSubject();
            System.out.println("Token válido. Email extraído: " + subject);
            return subject;
        } catch (JWTVerificationException exception) {
            System.out.println("Erro na verificação do token: " + exception.getMessage());
            return "";
        }
    }
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

