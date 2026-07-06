package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice.response.UserExceptionResponse;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserException;
import github.io.matheusfsantos.kr_server.user.application.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserExceptionResponse> handleUserException(UserException exception) {
        log.info("{} - handleUserException - message: end workflow with exception handler response, at: {}, status: {} ----> HTTP/END", getClass().getSimpleName(), DateUtils.getCurrent(), HttpStatus.valueOf(exception.getCode()));


        return ResponseEntity.status(HttpStatus.valueOf(exception.getCode())).body(
            UserExceptionResponse.builder()
                .message(exception.getMessage())
                .exceptionAt(LocalDateTime.now())
                .code(HttpStatus.valueOf(exception.getCode()))
            .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserExceptionResponse> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.info("{} - handleValidationException - message: end workflow with exception handler response, at: {}, status: {} ----> HTTP/END", getClass().getSimpleName(), DateUtils.getCurrent(), HttpStatus.UNPROCESSABLE_ENTITY);
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage(); /* get the first validation message in the "stack" */

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            UserExceptionResponse.builder()
                .message(message)
                .exceptionAt(LocalDateTime.now())
                .code(HttpStatus.UNPROCESSABLE_ENTITY)
            .build()
        );
    }
}
