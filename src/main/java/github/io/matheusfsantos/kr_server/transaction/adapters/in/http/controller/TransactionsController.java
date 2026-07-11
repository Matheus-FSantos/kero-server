package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface TransactionsController {
    ResponseEntity<Object> processTransaction(MultipartFile file, UUID id) throws TransactionException;
}
