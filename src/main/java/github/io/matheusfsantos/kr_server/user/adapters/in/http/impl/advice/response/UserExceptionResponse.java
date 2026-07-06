package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record UserExceptionResponse(
    String message,
    HttpStatus code,
    LocalDateTime exceptionAt
) { }
