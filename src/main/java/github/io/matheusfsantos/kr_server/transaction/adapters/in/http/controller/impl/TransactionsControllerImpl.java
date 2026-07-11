package github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.impl;

import github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.TransactionsController;
import github.io.matheusfsantos.kr_server.transaction.adapters.in.http.controller.impl.mapper.MultipartFileMapper;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.*;
import github.io.matheusfsantos.kr_server.transaction.application.core.utils.DateUtils;
import github.io.matheusfsantos.kr_server.transaction.application.ports.in.NewTransactionInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionsControllerImpl implements TransactionsController {
    private final NewTransactionInputPort newTransactionInputPort;

    @Override
    @PostMapping
    public ResponseEntity<Object> processTransaction(
        @RequestParam(name = "file") MultipartFile file,
        @AuthenticationPrincipal UUID id
    ) throws TransactionException {
        log.info("{} - processTransaction - message: init workflow to process a new transaction in kero infrastructure, file.name: {}, user.id: {}, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), file.getOriginalFilename(), id, DateUtils.getCurrent());
        InputStream is = MultipartFileMapper.toDomain(file);
        Object transaction = this.newTransactionInputPort.persist(is, file.getContentType(), id);

        log.info("{} - processTransaction - message: end workflow to process a new transaction in kero infrastructure, file.name: {}, at: {}, user.id: {}, status: 201 CREATED----> HTTP/END (POST)", getClass().getSimpleName(), file.getOriginalFilename(), id, DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}
