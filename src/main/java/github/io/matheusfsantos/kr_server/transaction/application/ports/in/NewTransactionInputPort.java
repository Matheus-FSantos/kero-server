package github.io.matheusfsantos.kr_server.transaction.application.ports.in;

import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.ExternalServiceException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.MissingContentException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.PayloadContractException;

import java.io.InputStream;
import java.util.UUID;

public interface NewTransactionInputPort {
    Object persist(InputStream file, String contentType, UUID id) throws IllegalFileCastException, ExternalServiceException, PayloadContractException, MissingContentException;
}
