package github.io.matheusfsantos.kr_server.auth.adapters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.GenerateTokenOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenerateTokenAdapter implements GenerateTokenOutputPort {
    @Value("${spring.api.security.token.secret}")
    private String SECRET;

    @Value("${spring.api.security.token.expiration-minutes}")
    private Long EXPIRATION_MINUTES;

    @Override
    public String generate(UserIdentity userIdentity) throws JwtException {
        try {
            log.info("{} - generate - message: generating an authentication token for the email user {}...", getClass().getSimpleName(), userIdentity.email());
            Algorithm algorithm = Algorithm.HMAC256(this.SECRET);

            return JWT.create()
                .withIssuer("kr-server")
                .withSubject(userIdentity.email())
                .withClaim("role", userIdentity.role())
                .withClaim("id", String.valueOf(userIdentity.id()))
                .withExpiresAt(this.getExpirationDate())
            .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("{} - generate - message: an error occurred while attempting to generate a token for the user with email {}...", getClass().getSimpleName(), userIdentity.email());
            log.debug("[{} - generate - ex.message: {}]", getClass().getSimpleName(), exception.getMessage(), exception);
            throw new JwtException("An error occurred while attempting to sign in; Please, wait a few moments and/or try again with different credentials.");
        }
    }

    private Instant getExpirationDate() { return Instant.now().plus(this.EXPIRATION_MINUTES, ChronoUnit.MINUTES); }
}
