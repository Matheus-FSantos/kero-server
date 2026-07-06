package github.io.matheusfsantos.kr_server.user.application.ports.out;

import github.io.matheusfsantos.kr_server.user.application.core.model.User;

import java.util.Optional;

public interface GetUserByEmailOutputPort {
    Optional<User> get(String email);
}
