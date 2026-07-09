package github.io.matheusfsantos.kr_server.auth.configuration;

import github.io.matheusfsantos.kr_server.auth.application.core.usecases.GetTokenInformationUseCase;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GetTokenInformationInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.DecryptTokenOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetTokenInformationInputPortConfiguration {
    @Bean
    GetTokenInformationInputPort getTokenInformationUseCaseConfiguration(DecryptTokenOutputPort decryptTokenOutputPort) {
        return new GetTokenInformationUseCase(decryptTokenOutputPort);
    }
}
