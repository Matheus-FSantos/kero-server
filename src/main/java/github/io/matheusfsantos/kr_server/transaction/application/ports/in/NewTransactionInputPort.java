package github.io.matheusfsantos.kr_server.transaction.application.ports.in;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;

import java.io.InputStream;

public interface NewTransactionInputPort {
    Transaction persist(InputStream file, String contentType);
}
