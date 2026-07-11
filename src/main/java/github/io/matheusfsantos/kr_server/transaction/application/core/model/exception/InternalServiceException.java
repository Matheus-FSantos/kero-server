package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class InternalServiceException extends TransactionException {
    public InternalServiceException(String message) {
        super(message, 502);
    }
}
