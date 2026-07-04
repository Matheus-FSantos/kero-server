package github.io.matheusfsantos.kr_server.transaction.configuration;

import github.io.matheusfsantos.kr_server.transaction.application.core.usecases.NewTransactionUseCase;
import github.io.matheusfsantos.kr_server.transaction.application.ports.in.NewTransactionInputPort;
import github.io.matheusfsantos.kr_server.transaction.application.ports.out.GetTransactionInfosOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewTransactionInputPortConfiguration {
    @Bean
    public NewTransactionInputPort newTransactionUseCaseConfiguration(GetTransactionInfosOutputPort getTransactionInfosOutputPort) {
        return new NewTransactionUseCase(getTransactionInfosOutputPort);
    }
}
