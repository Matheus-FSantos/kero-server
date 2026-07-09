package github.io.matheusfsantos.kr_server.auth.application.core.usecases;

import github.io.matheusfsantos.kr_server.auth.application.ports.in.ValidateTokenInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateTokenOutputPort;

public class ValidateTokenUseCase implements ValidateTokenInputPort {
    private final ValidateTokenOutputPort validateTokenOutputPort;

    public ValidateTokenUseCase(ValidateTokenOutputPort validateTokenOutputPort) {
        this.validateTokenOutputPort = validateTokenOutputPort;
    }

    @Override
    public Boolean validate(String token) {
        return this.validateTokenOutputPort.validate(token);
    }
}
