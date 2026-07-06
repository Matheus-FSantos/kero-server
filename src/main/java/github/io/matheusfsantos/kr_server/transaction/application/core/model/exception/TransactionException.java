package github.io.matheusfsantos.kr_server.transaction.application.core.model.exception;

public class TransactionException extends Exception {
    private Integer code;

    public TransactionException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() { return this.code; }
}
