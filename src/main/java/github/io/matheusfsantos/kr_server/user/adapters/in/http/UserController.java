package github.io.matheusfsantos.kr_server.user.adapters.in.http;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<Void> create(NewUserRequest user);
}
