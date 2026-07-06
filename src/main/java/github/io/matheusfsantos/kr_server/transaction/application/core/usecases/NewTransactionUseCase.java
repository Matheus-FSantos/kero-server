package github.io.matheusfsantos.kr_server.transaction.application.core.usecases;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.ExternalServiceException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.MissingContentException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.PayloadContractException;
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
    public Transaction persist(InputStream file, String contentType) throws IllegalFileCastException, ExternalServiceException, PayloadContractException, MissingContentException {
        Transaction transaction = this.getTransactionInfosOutputPort.get(file, contentType);
        return transaction;
    }
}
