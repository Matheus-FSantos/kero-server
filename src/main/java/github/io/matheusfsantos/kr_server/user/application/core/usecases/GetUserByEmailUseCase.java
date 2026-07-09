package github.io.matheusfsantos.kr_server.user.application.core.usecases;

import github.io.matheusfsantos.kr_server.user.application.ports.out.GetUserByEmailOutputPort;
import github.io.matheusfsantos.kr_server.user.application.core.model.User;
import github.io.matheusfsantos.kr_server.user.application.ports.in.GetUserByEmailInputPort;

import java.util.Optional;

public class GetUserByEmailUseCase implements GetUserByEmailInputPort {
    private final GetUserByEmailOutputPort getUserByEmailOutputPort;

    public GetUserByEmailUseCase(GetUserByEmailOutputPort getUserByEmailOutputPort) {
        this.getUserByEmailOutputPort = getUserByEmailOutputPort;
    }

    @Override
    public Optional<User> get(String email) {
        return this.getUserByEmailOutputPort.get(email);
    }
}
