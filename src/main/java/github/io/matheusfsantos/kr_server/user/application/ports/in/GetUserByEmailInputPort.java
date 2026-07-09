package github.io.matheusfsantos.kr_server.user.application.ports.in;

import github.io.matheusfsantos.kr_server.user.application.core.model.User;

import java.util.Optional;

public interface GetUserByEmailInputPort {
    Optional<User> get(String email);
}
