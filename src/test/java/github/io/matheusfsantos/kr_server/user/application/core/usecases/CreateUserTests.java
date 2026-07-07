package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserRole;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserAlreadyExistsException;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordEncoderOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PersisUserOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CreateUserTests {
    @Mock
    private GetUserByEmailOutputPort getUserByEmailOutputPort;
    @Mock
    private PasswordEncoderOutputPort passwordEncoderOutputPort;
    @Mock
    private PersisUserOutputPort persisUserOutputPort;
    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private NewUser newUser;

    @BeforeEach
    public void setup() {
        this.newUser = new NewUser("Matheus", "Santos", "matheus.fs.contato@gmail.com", "123456");

    }

    @Test
    void shouldBeReturnUserAlreadyExistsException() {
        User user = new User(UUID.randomUUID(), "Matheus", "Santos", "matheus.fs.contato@gmail.com", "123456", UserRole.USER, LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(this.getUserByEmailOutputPort.get(this.newUser.email())).thenReturn(Optional.of(user));
        UserAlreadyExistsException exception = Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            this.createUserUseCase.create(this.newUser);
        });

        Assertions.assertEquals(
            String.format("User with email '%s' already exists", this.newUser.email()),
            exception.getMessage()
        );
    }

    @Test
    void shouldBeReturnNothingBecauseUserWillBeSavedWithoutProblems() throws UserAlreadyExistsException {
        Mockito.when(this.getUserByEmailOutputPort.get(this.newUser.email())).thenReturn(Optional.empty());
        Mockito.when(this.passwordEncoderOutputPort.encode(this.newUser.password())).thenReturn(this.newUser.password());

        this.createUserUseCase.create(newUser);
        Mockito.verify(this.persisUserOutputPort, Mockito.times(1)).persist(Mockito.any(NewUser.class));
    }
}
