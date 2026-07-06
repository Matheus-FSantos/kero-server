package github.io.matheusfsantos.kr_server;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModuleDependencyTests {
    @Test
    void shouldBeValidateIfDependenceExistsBetweenModules() {
        ApplicationModules modules = ApplicationModules.of(KrServerApplication.class);
        modules.verify();
    }
}
