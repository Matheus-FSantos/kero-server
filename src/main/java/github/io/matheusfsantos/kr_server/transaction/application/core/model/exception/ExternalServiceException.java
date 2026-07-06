package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class ExternalServiceException extends TransactionException {
    public ExternalServiceException(String message) {
        super(message, 502);
    }
}
