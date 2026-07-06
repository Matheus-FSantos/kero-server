package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserRole;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserNotFoundException;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordValidateOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ValidateUserCredentialsTests {
    @Mock
    private GetUserByEmailOutputPort getUserByEmailOutputPort;
    @Mock
    private PasswordValidateOutputPort passwordValidateOutputPort;
    @InjectMocks
    private ValidateUserCredentialsUseCase validateUserCredentialsUseCase;

    private User user;

    @BeforeEach
    public void setup() {
        this.user = new User(UUID.randomUUID(), "Matheus", "Santos", "matheus.fs.contato@gmail.com", "123456", UserRole.USER, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void shouldReturnUserNotFoundException() {
        Credentials credentials = new Credentials("matheus.fs.contato@gmail.com", "123456");

        Mockito.when(this.getUserByEmailOutputPort.get(credentials.email())).thenReturn(Optional.empty());
        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> {
            this.validateUserCredentialsUseCase.validate(credentials);
        });

        Assertions.assertEquals(String.format("User with email %s not found", credentials.email()), exception.getMessage());
    }

    @Test
    void shouldReturnEmptyBecausePasswordsNotMatch() throws UserNotFoundException {
        Credentials credentials = new Credentials("matheus.fs.contato@gmail.com", "1234567");

        Mockito.when(this.getUserByEmailOutputPort.get(credentials.email())).thenReturn(Optional.of(this.user));
        Mockito.when(this.passwordValidateOutputPort.compare(credentials.password(), this.user.password())).thenReturn(false);

        Optional<UserIdentity> userIdentity = this.validateUserCredentialsUseCase.validate(credentials);
        Assertions.assertTrue(userIdentity.isEmpty());
    }

    @Test
    void shouldBeReturnUserIdentity() throws UserNotFoundException {
        Credentials credentials = new Credentials("matheus.fs.contato@gmail.com", "123456");

        Mockito.when(this.getUserByEmailOutputPort.get(credentials.email())).thenReturn(Optional.of(this.user));
        Mockito.when(this.passwordValidateOutputPort.compare(credentials.password(), this.user.password())).thenReturn(true);

        Optional<UserIdentity> userIdentity = this.validateUserCredentialsUseCase.validate(credentials);
        Assertions.assertTrue(userIdentity.isPresent());
        Assertions.assertEquals(credentials.email(), userIdentity.get().email());
    }
}
