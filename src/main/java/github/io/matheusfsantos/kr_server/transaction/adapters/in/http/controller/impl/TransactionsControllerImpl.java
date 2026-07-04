package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.impl;

import github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.TransactionsController;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.utils.DateUtils;
import github.io.matheusfsantos.kr_server.transaction.application.ports.in.NewTransactionInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionsControllerImpl implements TransactionsController {
    private final NewTransactionInputPort newTransactionInputPort;

    @Override
    @PostMapping
    public ResponseEntity<Transaction> processTransaction(@RequestPart(name = "file") MultipartFile file) throws IOException {
        log.info("{} - processTransaction - message: init workflow to process a new transaction in kero infrastructure, file.name: {}, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), file.getOriginalFilename(), DateUtils.getCurrent());
        Transaction transaction = this.newTransactionInputPort.persist(file.getInputStream(), file.getContentType());

        log.info("{} - processTransaction - message: end workflow to process a new transaction in kero infrastructure, file.name: {}, at: {}, status: 201 CREATED----> HTTP/END (POST)", getClass().getSimpleName(), file.getOriginalFilename(), DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
