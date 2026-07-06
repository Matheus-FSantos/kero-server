package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.advice;

import github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.advice.response.TransactionExceptionResponse;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class TransactionControllerAdvice {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<TransactionExceptionResponse> transactionExceptionHandler(TransactionException exception) {
        TransactionExceptionResponse response = TransactionExceptionResponse.builder()
            .message(exception.getMessage())
            .exceptionAt(LocalDateTime.now())
            .code(HttpStatus.valueOf(exception.getCode()))
        .build();

        return ResponseEntity.status(HttpStatus.valueOf(exception.getCode())).body(response);
    }
}
