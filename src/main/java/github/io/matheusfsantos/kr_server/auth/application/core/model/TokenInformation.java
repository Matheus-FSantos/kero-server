package github.io.matheusfsantos.kr_server.auth.application.core.model;

import java.util.UUID;

public record TokenInformation(
    UUID id,
    String email,
    String role
) { }
