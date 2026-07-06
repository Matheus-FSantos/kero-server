package github.io.matheusfsantos.kr_server.transaction.application.ports.out;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.ExternalServiceException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.MissingContentException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.PayloadContractException;

import java.io.InputStream;

public interface GetTransactionInfosOutputPort {
    Transaction get(InputStream file, String contentType) throws IllegalFileCastException, ExternalServiceException, PayloadContractException, MissingContentException;
}
