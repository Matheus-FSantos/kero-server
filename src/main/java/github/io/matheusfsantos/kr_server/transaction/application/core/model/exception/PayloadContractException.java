package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class PayloadContractException extends TransactionException {
    public PayloadContractException(String message) {
        super(message, 500);
    }
}
