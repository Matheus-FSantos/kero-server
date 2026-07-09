package github.io.matheusfsantos.kr_server.auth.adapters.out.client.response;

public record UserIdentityResponse(
    String id,
    String email,
    String role
) { }
