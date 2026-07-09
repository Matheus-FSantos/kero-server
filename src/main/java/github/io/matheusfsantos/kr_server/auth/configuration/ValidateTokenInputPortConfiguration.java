package github.io.matheusfsantos.kr_server.auth.configuration;

import github.io.matheusfsantos.kr_server.auth.application.core.usecases.ValidateTokenUseCase;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.ValidateTokenInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateTokenOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateTokenInputPortConfiguration {
    @Bean
    ValidateTokenInputPort validateTokenUseCaseConfiguration(ValidateTokenOutputPort validateTokenOutputPort) {
        return new ValidateTokenUseCase(validateTokenOutputPort);
    }
}
