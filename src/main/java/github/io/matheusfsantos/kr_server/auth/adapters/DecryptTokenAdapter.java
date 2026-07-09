package github.io.matheusfsantos.kr_server.auth.adapters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.DecryptTokenOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DecryptTokenAdapter implements DecryptTokenOutputPort {
    @Override
    public TokenInformation decrypt(String token) throws JwtException {
        try {
            log.info("{} - decrypt - message: decoding informed jwt token...", getClass().getSimpleName());

            DecodedJWT decoded = JWT.decode(token);
            return new TokenInformation(
                UUID.fromString(decoded.getClaim("id").asString()),
                decoded.getSubject(),
                decoded.getClaim("role").asString()
            );
        } catch (JWTDecodeException e) {
            log.info("{} - decrypt - message: an error occurred while attempting to decode the provided token.", getClass().getSimpleName());
            throw new JwtException("An error occurred while attempting to decode the provided token; Please, try again and/or provide a new token.");
        }
    }
}
