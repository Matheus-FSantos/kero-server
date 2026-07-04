package github.io.matheusfsantos.kr_server.user.adapters;

import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordEncoderOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordValidateOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordEncoderAdapter implements PasswordEncoderOutputPort, PasswordValidateOutputPort {
    @Override
    public String encode(String password) { return BCrypt.hashpw(password, BCrypt.gensalt()); }

    @Override
    public Boolean compare(String password, String toCompare) { return BCrypt.checkpw(password, toCompare); }
}
