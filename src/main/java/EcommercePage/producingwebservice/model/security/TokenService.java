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

    // Define a chave secreta usada para assinar e verificar os tokens JWT
    private String secret = "segredinho";

    // Método para gerar um token JWT com base nas informações do solicitante
    public String generateToken(Solicitante solicitante) {
        try {
            // Define o algoritmo de criptografia HMAC com a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Cria o token JWT com o emissor, assunto, expiração e assina com o algoritmo
            String token = JWT.create()
                .withIssuer("auth")
                .withSubject(solicitante.getEmail())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
            // Lança uma exceção em caso de erro na geração do token
            throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
        }
    }

    // Método para validar um token JWT e obter o assunto (email) dele
    public String validateToken(String token) {
        try {
            // Define o algoritmo de criptografia HMAC com a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Cria um verificador JWT com o algoritmo e configura o emissor esperado
            var verifier = JWT.require(algorithm)
                .withIssuer("auth")
                .build();

            // Verifica o token JWT e obtém o objeto decodificado
            var decodedJWT = verifier.verify(token);
            String subject = decodedJWT.getSubject();
            System.out.println("Token válido. Email extraído: " + subject);
            return subject;

        } catch (JWTVerificationException exception) {
            // Captura e imprime uma mensagem de erro caso a verificação falhe
            System.out.println("Erro na verificação do token: " + exception.getMessage());
            return "";
        }
    }

    // Método privado para calcular a data de expiração do token (2 horas a partir de agora)
    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

