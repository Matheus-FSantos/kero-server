package github.io.matheusfsantos.kr_server.user.application.ports.out;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;

public interface PersisUserOutputPort {
    void persist(NewUser user);
}
