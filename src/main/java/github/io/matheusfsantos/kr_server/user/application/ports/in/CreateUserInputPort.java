package github.io.matheusfsantos.kr_server.user.application.ports.in;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserAlreadyExistsException;

public interface CreateUserInputPort {
    void create(NewUser user) throws UserAlreadyExistsException;
}
