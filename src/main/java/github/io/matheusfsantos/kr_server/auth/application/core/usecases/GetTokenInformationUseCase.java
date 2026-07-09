package github.io.matheusfsantos.kr_server.auth.application.core.usecases;

import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.JwtException;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GetTokenInformationInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.DecryptTokenOutputPort;

public class GetTokenInformationUseCase implements GetTokenInformationInputPort {
    private final DecryptTokenOutputPort decryptTokenOutputPort;

    public GetTokenInformationUseCase(DecryptTokenOutputPort decryptTokenOutputPort) {
        this.decryptTokenOutputPort = decryptTokenOutputPort;
    }

    @Override
    public TokenInformation get(String token) throws JwtException {
        return this.decryptTokenOutputPort.decrypt(token);
    }
}
