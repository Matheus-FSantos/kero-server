package github.io.matheusfsantos.kr_server.user.application.core.model.exception;

public class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException(String message) {
        super(message, 409);
    }
}
