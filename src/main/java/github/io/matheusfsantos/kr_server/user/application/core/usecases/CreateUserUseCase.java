package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordEncoderOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PersisUserOutputPort;

public class CreateUserUseCase implements CreateUserInputPort {
    private final PasswordEncoderOutputPort passwordEncoderOutputPort;
    private final PersisUserOutputPort persisUserOutputPort;

    public CreateUserUseCase(PasswordEncoderOutputPort passwordEncoderOutputPort, PersisUserOutputPort persisUserOutputPort) {
        this.persisUserOutputPort = persisUserOutputPort;
        this.passwordEncoderOutputPort = passwordEncoderOutputPort;
    }

    @Override
    public void create(NewUser user) {
        String encodedPassword = this.passwordEncoderOutputPort.encode(user.password());
        NewUser withEncodedPassword = new NewUser(user.firstName(), user.lastName(), user.email(), encodedPassword);
        this.persisUserOutputPort.persist(withEncodedPassword);
    }
}
