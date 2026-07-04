package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.mapper;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;

public class NewUserRequestMapper {
    private NewUserRequestMapper() { }

    public static NewUser toDomain(NewUserRequest request) {
        return new NewUser(
            request.firstName(),
            request.lastName(),
            request.email(),
            request.password()
        );
    }
}
