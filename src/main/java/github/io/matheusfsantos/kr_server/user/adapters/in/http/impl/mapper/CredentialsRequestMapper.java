package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.mapper;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.user.application.core.model.Credentials;

public class CredentialsRequestMapper {
    private CredentialsRequestMapper() { }

    public static Credentials toDomain(CredentialsRequest request) {
        return new Credentials(request.email(), request.password());
    }
}
