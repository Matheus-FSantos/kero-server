package github.io.matheusfsantos.kr_server.user.adapters;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.UserRepository;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.UserEntity;
import github.io.matheusfsantos.kr_server.user.adapters.out.repository.mapper.UserEntityMapper;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserByEmailAdapter implements GetUserByEmailOutputPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> get(String email) {
        log.info("{} - get - message: searching for user by email in database, email: {}", getClass().getSimpleName(), email);
        Optional<UserEntity> entity = this.userRepository.findByEmail(email);

        log.info("{} - get - message: {} user found with email {}", getClass().getSimpleName(), entity.isPresent() ? 1 : 0, email);
        return entity.map(UserEntityMapper::toDomain);
    }
}
