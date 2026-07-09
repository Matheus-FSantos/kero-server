package github.io.matheusfsantos.kr_server.auth.application.ports.out;

import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;

public interface DecryptTokenOutputPort {
    TokenInformation decrypt(String token) throws JwtException;
}
