package github.io.matheusfsantos.kr_server.auth.adapters.in.http.advice.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record AuthExceptionResponse(
    String message,
    HttpStatus code,
    LocalDateTime exceptionAt
) { }
