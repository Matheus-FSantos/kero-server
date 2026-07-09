package github.io.matheusfsantos.kr_server.user.configuration;

import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.core.usecases.GetUserByEmailUseCase;
import github.io.matheusfsantos.kr_server.user.application.ports.in.GetUserByEmailInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetUserByEmailInputPortConfiguration {
    @Bean
    GetUserByEmailInputPort getUserByEmailUseCaseConfiguration(GetUserByEmailOutputPort getUserByEmailOutputPort) {
        return new GetUserByEmailUseCase(getUserByEmailOutputPort);
    }
}
