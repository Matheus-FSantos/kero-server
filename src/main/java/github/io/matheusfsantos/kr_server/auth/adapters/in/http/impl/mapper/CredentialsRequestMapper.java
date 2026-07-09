package github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.mapper;

import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.CredentialsRequest;

public class CredentialsRequestMapper {
    private CredentialsRequestMapper() { }

    public static Credentials toDomain(CredentialsRequest request) {
        return new Credentials(request.email(), request.password());
    }

    public static CredentialsRequest toRequest(Credentials credentials) {
        return new CredentialsRequest(credentials.email(), credentials.password());
    }
}
