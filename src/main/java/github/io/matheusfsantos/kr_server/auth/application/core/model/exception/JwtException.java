package github.io.matheusfsantos.kr_server.auth.application.core.model.exception;

public class JwtException extends AuthException {
    public JwtException(String message) {
        super(message, 500);
    }
}
