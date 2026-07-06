package github.io.matheusfsantos.kr_server.user.adapters;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.UserEntity;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.mapper.NewUserMapper;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.UserRepository;
import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PersisUserOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersisUserAdapter implements PersisUserOutputPort {
    private final UserRepository userRepository;

    @Override
    public void persist(NewUser user) {
        log.info("{} - persist - message: saving user with email = {} to the database...", getClass().getSimpleName(), user.email());
        UserEntity readyToSave = NewUserMapper.toEntity(user);

        this.userRepository.save(readyToSave);
        log.info("{} - persist - message: user saved successfully!", getClass().getSimpleName());
    }
}
