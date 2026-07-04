package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TransactionsController {
    ResponseEntity<Transaction> processTransaction(MultipartFile file) throws IOException;
}
