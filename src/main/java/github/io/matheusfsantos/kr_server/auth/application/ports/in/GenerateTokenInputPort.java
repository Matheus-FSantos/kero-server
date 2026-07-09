package github.io.matheusfsantos.kr_server.auth.application.ports.in;

import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.InternalServiceException;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;

public interface GenerateTokenInputPort {
    String fetch(Credentials credentials) throws InternalServiceException, JwtException;
}
