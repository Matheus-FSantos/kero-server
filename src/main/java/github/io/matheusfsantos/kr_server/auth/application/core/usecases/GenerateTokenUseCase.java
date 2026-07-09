package github.io.matheusfsantos.kr_server.auth.application.core.usecases;

import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.InternalServiceException;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GenerateTokenInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.GenerateTokenOutputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateCredentialsOutputPort;

public class GenerateTokenUseCase implements GenerateTokenInputPort {
    private final ValidateCredentialsOutputPort validateCredentialsOutputPort;
    private final GenerateTokenOutputPort generateTokenOutputPort;

    public GenerateTokenUseCase(ValidateCredentialsOutputPort validateCredentialsOutputPort, GenerateTokenOutputPort generateTokenOutputPort) {
        this.validateCredentialsOutputPort = validateCredentialsOutputPort;
        this.generateTokenOutputPort = generateTokenOutputPort;
    }

    @Override
    public String fetch(Credentials credentials) throws InternalServiceException, JwtException {
        UserIdentity identity = this.validateCredentialsOutputPort.validate(credentials);
        return this.generateTokenOutputPort.generate(identity);
    }
}
