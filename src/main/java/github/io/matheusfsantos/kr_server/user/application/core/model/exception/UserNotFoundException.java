package github.io.matheusfsantos.kr_server.user.application.core.model.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message, 404);
    }
}
