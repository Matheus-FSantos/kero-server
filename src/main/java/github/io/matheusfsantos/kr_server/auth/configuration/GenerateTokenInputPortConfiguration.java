package github.io.matheusfsantos.kr_server.auth.configuration;

import github.io.matheusfsantos.kr_server.auth.application.core.usecases.GenerateTokenUseCase;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GenerateTokenInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.GenerateTokenOutputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateCredentialsOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerateTokenInputPortConfiguration {
    @Bean
    GenerateTokenInputPort generateTokenUseCaseConfiguration(ValidateCredentialsOutputPort validateCredentialsOutputPort, GenerateTokenOutputPort generateTokenOutputPort) {
        return new GenerateTokenUseCase(validateCredentialsOutputPort, generateTokenOutputPort);
    }
}
