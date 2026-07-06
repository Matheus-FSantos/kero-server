package github.io.matheusfsantos.kr_server.user.configuration;

import github.io.matheusfsantos.kr_server.user.application.core.usecases.ValidateUserCredentialsUseCase;
import github.io.matheusfsantos.kr_server.user.application.ports.in.ValidateUserCredentialsInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.out.PasswordValidateOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateUserCredentialsInputPortConfiguration {
    @Bean
    ValidateUserCredentialsInputPort validateUserCredentialsUseCaseConfiguration(GetUserByEmailOutputPort getUserByEmailOutputPort, PasswordValidateOutputPort passwordValidateOutputPort) {
        return new ValidateUserCredentialsUseCase(getUserByEmailOutputPort, passwordValidateOutputPort);
    }
}
