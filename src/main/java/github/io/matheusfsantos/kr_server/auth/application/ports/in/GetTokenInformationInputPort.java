package github.io.matheusfsantos.kr_server.auth.application.ports.in;

import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;

public interface GetTokenInformationInputPort {
    TokenInformation get(String token) throws JwtException;
}
