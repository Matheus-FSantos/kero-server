package github.io.matheusfsantos.kr_server.user.adapter;

import github.io.matheusfsantos.kr_server.user.adapters.PersisUserAdapter;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.UserRepository;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.UserEntity;
import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersistUserAdapterTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PersisUserAdapter persisUserAdapter;

    @Test
    void shouldBePersistANewUser() {
        NewUser newUser = new NewUser("Matheus", "Santos", "matheus.fs.contato@gmail.com", "123456");
        ArgumentCaptor<UserEntity> argumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        this.persisUserAdapter.persist(newUser);
        Mockito.verify(this.userRepository, Mockito.times(1)).save(argumentCaptor.capture());
        UserEntity userEntity = argumentCaptor.getValue();

        Assertions.assertEquals(newUser.email(), userEntity.getEmail());
    }
}
