package github.io.matheusfsantos.kr_server.user.application.ports.out;

public interface PasswordEncoderOutputPort {
    String encode(String password);
}
