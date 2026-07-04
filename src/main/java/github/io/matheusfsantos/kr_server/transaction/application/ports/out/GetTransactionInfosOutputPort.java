package github.io.matheusfsantos.kr_server.transaction.application.ports.out;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;

import java.io.InputStream;

public interface GetTransactionInfosOutputPort {
    Transaction get(InputStream file, String contentType);
}
