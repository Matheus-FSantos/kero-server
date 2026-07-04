package github.io.matheusfsantos.kr_server.user.application.core.utils;

import github.io.matheusfsantos.kr_server.user.application.core.model.UserRole;

public class UserRoleUtils {
    private UserRoleUtils() { }

    public static int getRoleId(String n) {
        for(UserRole r : UserRole.values())
            if(r.name().equals(n)) return r.getId();

        return 0;
    }
}
