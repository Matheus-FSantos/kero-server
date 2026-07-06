package github.io.matheusfsantos.kr_server.user.adapters.out.repository.mapper;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.UserEntity;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;

public class UserEntityMapper {
    private UserEntityMapper() { }

    public static User toDomain(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getRole(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
