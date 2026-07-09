package github.io.matheusfsantos.kr_server.auth.adapters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateTokenOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateTokenAdapter implements ValidateTokenOutputPort {
    @Value("${spring.api.security.token.secret}")
    private String SECRET;

    @Override
    public Boolean validate(String token) {
        try {
            log.info("{} - validate - message: validating the provided token...", getClass().getSimpleName());
            Algorithm algorithm = Algorithm.HMAC256(this.SECRET);

            JWT.require(algorithm)
                .withIssuer("kr-server")
                .build()
            .verify(token);

            return true;
        } catch (Exception e) {
            log.warn("{} - validate - message: invalid or expired token.", getClass().getSimpleName());
            return false;
        }
    }
}
