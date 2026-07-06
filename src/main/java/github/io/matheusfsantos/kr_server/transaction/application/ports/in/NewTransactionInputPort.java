package github.io.matheusfsantos.kr_server.transaction.application.ports.in;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.ExternalServiceException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.MissingContentException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.PayloadContractException;

import java.io.InputStream;

public interface NewTransactionInputPort {
    Transaction persist(InputStream file, String contentType) throws IllegalFileCastException, ExternalServiceException, PayloadContractException, MissingContentException;
}
