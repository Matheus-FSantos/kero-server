package github.io.matheusfsantos.kr_server.user.application.core.model;

public record NewUser(
    String firstName,
    String lastName,
    String email,
    String password
) { }
