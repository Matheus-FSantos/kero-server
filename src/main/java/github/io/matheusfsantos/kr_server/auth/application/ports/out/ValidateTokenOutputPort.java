package github.io.matheusfsantos.kr_server.auth.application.ports.out;

public interface ValidateTokenOutputPort {
    Boolean validate(String token);
}
