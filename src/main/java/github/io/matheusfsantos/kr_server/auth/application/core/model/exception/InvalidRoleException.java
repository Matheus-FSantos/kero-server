package github.io.matheusfsantos.kr_server.auth.application.core.model.exception;

public class InvalidRoleException extends AuthException {
    public InvalidRoleException(String message) {
        super(message, 422);
    }
}
