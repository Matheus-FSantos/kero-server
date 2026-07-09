package github.io.matheusfsantos.kr_server.auth.application.core.model.exception;

public class InternalServiceException extends AuthException {
    public InternalServiceException(String message) {
        super(message, 502);
    }
}
