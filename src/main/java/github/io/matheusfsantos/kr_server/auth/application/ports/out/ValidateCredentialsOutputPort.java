package github.io.matheusfsantos.kr_server.auth.application.ports.out;

import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.InternalServiceException;

public interface ValidateCredentialsOutputPort {
    UserIdentity validate(Credentials credentials) throws InternalServiceException;
}
