package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserAlreadyExistsException;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordEncoderOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PersisUserOutputPort;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateUserUseCase implements CreateUserInputPort {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    private final PasswordEncoderOutputPort passwordEncoderOutputPort;
    private final GetUserByEmailOutputPort getUserByEmailOutputPort;
    private final PersisUserOutputPort persisUserOutputPort;

    public CreateUserUseCase(PasswordEncoderOutputPort passwordEncoderOutputPort, GetUserByEmailOutputPort getUserByEmailOutputPort, PersisUserOutputPort persisUserOutputPort) {
        this.persisUserOutputPort = persisUserOutputPort;
        this.getUserByEmailOutputPort = getUserByEmailOutputPort;
        this.passwordEncoderOutputPort = passwordEncoderOutputPort;
    }

    @Override
    public void create(NewUser user) throws UserAlreadyExistsException {
        Optional<User> existentUser = this.getUserByEmailOutputPort.get(user.email());
        if(existentUser.isPresent()) {
            this.logger.log(Level.INFO, "{0} - create - message: operation rejected, user with email {1} already exists.", new Object[] { getClass().getSimpleName(), user.email() });
            throw new UserAlreadyExistsException(String.format("User with email '%s' already exists", user.email()));
        }

        String encodedPassword = this.passwordEncoderOutputPort.encode(user.password());
        NewUser withEncodedPassword = new NewUser(user.firstName(), user.lastName(), user.email(), encodedPassword);
        this.persisUserOutputPort.persist(withEncodedPassword);
    }
}
