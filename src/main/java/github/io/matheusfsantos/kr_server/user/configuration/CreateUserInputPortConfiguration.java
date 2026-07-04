package github.io.matheusfsantos.kr_server.user.configuration;

import github.io.matheusfsantos.kr_server.user.application.core.usecases.CreateUserUseCase;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordEncoderOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PersisUserOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateUserInputPortConfiguration {
    @Bean
    CreateUserInputPort createUserUseCaseConfiguration(PasswordEncoderOutputPort passwordEncoderOutputPort, PersisUserOutputPort persisUserOutputPort) {
        return new CreateUserUseCase(passwordEncoderOutputPort, persisUserOutputPort);
    }
}
