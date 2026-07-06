package github.io.matheusfsantos.kr_server.user.adapters.in.http;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserException;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<Void> create(NewUserRequest user) throws UserException;
    ResponseEntity<UserIdentity> validateCredentials(CredentialsRequest credentials) throws UserException;
}
