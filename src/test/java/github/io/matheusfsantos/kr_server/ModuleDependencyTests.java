package github.io.matheusfsantos.kr_server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModuleDependencyTests {
    @BeforeAll
    public static void setup() {
        System.setProperty("spring.profiles.active", "test");
        System.setProperty("APP_PROFILE", "development");
    }

    @Test
    void shouldBeValidateIfDependenceExistsBetweenModules() {
        ApplicationModules modules = ApplicationModules.of(KrServerApplication.class);
        modules.verify();
    }
}
