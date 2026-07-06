package github.io.matheusfsantos.kr_server.user.application.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String password,
    UserRole role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
