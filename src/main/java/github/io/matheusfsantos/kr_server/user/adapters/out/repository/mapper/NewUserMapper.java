package github.io.matheusfsantos.kr_server.user.adapters.out.repository.mapper;

import github.io.matheusfsantos.kr_server.user.adapters.out.repository.entity.User;
import github.io.matheusfsantos.kr_server.user.application.core.model.NewUser;

public class NewUserMapper {
    private NewUserMapper() { }

    public static User toEntity(NewUser user) {
        return new User(
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
