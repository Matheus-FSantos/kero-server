package github.io.matheusfsantos.kr_server.user.application.ports.in;

import github.io.matheusfsantos.kr_server.user.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserNotFoundException;

import java.util.Optional;

public interface ValidateUserCredentialsInputPort {
    Optional<UserIdentity> validate(Credentials credentials) throws UserNotFoundException;
}
