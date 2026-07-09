package github.io.matheusfsantos.kr_server.auth.adapters.in.http.advice;

import github.io.matheusfsantos.kr_server.auth.adapters.in.http.advice.response.AuthExceptionResponse;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.AuthException;
import github.io.matheusfsantos.kr_server.auth.application.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class AuthControllerAdvice {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<AuthExceptionResponse> handleAuthException(AuthException exception) {
        log.info("{} - handleAuthException - message: end workflow with exception handler response, at: {}, status: {} ----> HTTP/END", getClass().getSimpleName(), DateUtils.getCurrent(), HttpStatus.valueOf(exception.getCode()));

        AuthExceptionResponse response = AuthExceptionResponse.builder()
            .message(exception.getMessage())
            .exceptionAt(LocalDateTime.now())
            .code(HttpStatus.valueOf(exception.getCode()))
        .build();

        return ResponseEntity.status(HttpStatus.valueOf(exception.getCode())).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.info("{} - handleMethodArgumentNotValidException - message: end workflow with exception handler response, at: {}, status: {} ----> HTTP/END", getClass().getSimpleName(), DateUtils.getCurrent(), HttpStatus.UNPROCESSABLE_ENTITY);
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            AuthExceptionResponse.builder()
                .message(message)
                .exceptionAt(LocalDateTime.now())
                .code(HttpStatus.UNPROCESSABLE_ENTITY)
            .build()
        );
    }
}
