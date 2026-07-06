package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.advice.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record TransactionExceptionResponse(
    String message,
    HttpStatus code,
    LocalDateTime exceptionAt
) { }
