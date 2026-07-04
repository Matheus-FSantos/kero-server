package github.io.matheusfsantos.kr_server.user.application.ports.out;

public interface PasswordValidateOutputPort {
    Boolean compare(String password, String toCompare);
}
