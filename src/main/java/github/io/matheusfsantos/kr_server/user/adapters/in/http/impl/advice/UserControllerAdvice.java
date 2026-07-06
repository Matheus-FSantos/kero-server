package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.advice.response.UserExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserExceptionResponse> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage(); /* get the first validation message in the "stack" */

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            UserExceptionResponse.builder()
                .message(message)
                .code(HttpStatus.UNPROCESSABLE_ENTITY)
                .exceptionAt(LocalDateTime.now())
            .build()
        );
    }
}
