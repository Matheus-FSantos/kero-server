package github.io.matheusfsantos.kr_server.user.application.core.model;

public record UserIdentity(
    String email,
    UserRole role
) { }
