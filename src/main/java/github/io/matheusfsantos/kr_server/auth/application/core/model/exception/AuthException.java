package github.io.matheusfsantos.kr_server.auth.application.core.model.exception;

public class AuthException extends Exception {
    private final Integer code;

    public AuthException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() { return code; }
}
