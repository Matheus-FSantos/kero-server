package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserNotFoundException;
import github.io.matheusfsantos.kr_server.user.application.ports.in.ValidateUserCredentialsInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordValidateOutputPort;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateUserCredentialsUseCase implements ValidateUserCredentialsInputPort {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    private final GetUserByEmailOutputPort getUserByEmailOutputPort;
    private final PasswordValidateOutputPort passwordValidateOutputPort;

    public ValidateUserCredentialsUseCase(GetUserByEmailOutputPort getUserByEmailOutputPort, PasswordValidateOutputPort passwordValidateOutputPort) {
        this.getUserByEmailOutputPort = getUserByEmailOutputPort;
        this.passwordValidateOutputPort = passwordValidateOutputPort;
    }

    @Override
    public Optional<UserIdentity> validate(Credentials credentials) throws UserNotFoundException {
        Optional<User> user = this.getUserByEmailOutputPort.get(credentials.email());
        if(user.isEmpty()) throw new UserNotFoundException(String.format("User with email %s not found", credentials.email()));

        this.logger.log(Level.INFO, "{0} - validate - message: comparing the provided password with the password found in the database for the user with email {1}...", new Object[] { getClass().getSimpleName(), credentials.email() });
        Boolean equals = this.passwordValidateOutputPort.compare(credentials.password(), user.get().password());
        if(Boolean.FALSE.equals(equals)) return Optional.empty();

        return Optional.of(new UserIdentity(user.get().id(), credentials.email(), user.get().role()));
    }
}
