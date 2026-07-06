package github.io.matheusfsantos.kr_server.user.application.core.model.exception;

public class UserException extends Exception {
    private final Integer code;

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() { return this.code; }
}
