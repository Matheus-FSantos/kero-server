package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class MissingContentException extends TransactionException {
    public MissingContentException(String message) {
        super(message, 500);
    }
}
