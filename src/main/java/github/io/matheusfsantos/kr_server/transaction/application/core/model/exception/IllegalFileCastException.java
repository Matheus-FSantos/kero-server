package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class IllegalFileCastException extends TransactionException {
    public IllegalFileCastException(String message) {
        super(message, 422);
    }
}
