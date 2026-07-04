package github.io.matheusfsantos.kr_server.user.application.ports.in;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;

public interface CreateUserInputPort {
    void create(NewUser user);
}
