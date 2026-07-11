package github.io.matheusfsantos.kr_server.auth.adapters.in.http;

import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.AuthException;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<String> signIn(CredentialsRequest credentials) throws AuthException;
}
