package github.io.matheusfsantos.kr_server.transaction.application.core.usecases;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.ports.in.NewTransactionInputPort;
import github.io.matheusfsantos.kr_server.transaction.application.ports.out.GetTransactionInfosOutputPort;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewTransactionUseCase implements NewTransactionInputPort {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final GetTransactionInfosOutputPort getTransactionInfosOutputPort;

    public NewTransactionUseCase(GetTransactionInfosOutputPort getTransactionInfosOutputPort) {
        this.getTransactionInfosOutputPort = getTransactionInfosOutputPort;
    }

    @Override
    public Transaction persist(InputStream file, String contentType) {
        Transaction transaction = this.getTransactionInfosOutputPort.get(file, contentType);
        if(transaction != null) this.logger.log(Level.INFO, "{0} - persist - message: success to get transaction information, transaction.recipient.name: {1}", new Object[]{ getClass().getSimpleName(), transaction.recipient().name() });

        return transaction;
    }
}
