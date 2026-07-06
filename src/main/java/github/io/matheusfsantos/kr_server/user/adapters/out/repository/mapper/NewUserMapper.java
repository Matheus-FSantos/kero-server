package github.io.matheusfsantos.kr_server.user.adapters.out.repository.mapper;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.UserEntity;
import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;

public class NewUserMapper {
    private NewUserMapper() { }

    public static UserEntity toEntity(NewUser user) {
        return new UserEntity(
            null,
            user.firstName(),
            user.lastName(),
            user.email(),
            user.password(),
            null,
            null,
            null
        );
    }
}
