package github.io.matheusfsantos.kr_server.auth.adapters.out.client.mapper;

import github.io.matheusfsantos.kr_server.auth.adapters.out.client.response.UserIdentityResponse;
import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;

import java.util.UUID;

public class UserIdentityResponseMapper {
    private UserIdentityResponseMapper() { }

    public static UserIdentity toDomain(UserIdentityResponse response) {
        return new UserIdentity(UUID.fromString(response.id()), response.email(), response.role());
    }
}
