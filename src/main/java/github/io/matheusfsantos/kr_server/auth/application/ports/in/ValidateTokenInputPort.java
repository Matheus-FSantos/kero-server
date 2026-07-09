package github.io.matheusfsantos.kr_server.auth.application.ports.in;

public interface ValidateTokenInputPort {
    Boolean validate(String token);
}
