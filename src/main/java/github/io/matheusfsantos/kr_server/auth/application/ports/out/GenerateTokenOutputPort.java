package github.io.matheusfsantos.kr_server.auth.application.ports.out;

import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;

public interface GenerateTokenOutputPort {
    String generate(UserIdentity userIdentity) throws JwtException;
}
