package github.io.matheusfsantos.kr_server.user.application.core.model;

public enum UserRole {
    USER(1),
    ADMINISTRATOR(2);
    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
