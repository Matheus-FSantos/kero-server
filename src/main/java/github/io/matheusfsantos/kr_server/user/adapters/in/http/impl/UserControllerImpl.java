package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl;

import github.io.matheusfsantos.kr_server.transaction.application.core.utils.DateUtils;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.UserController;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.mapper.NewUserRequestMapper;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserControllerImpl implements UserController {
    private final CreateUserInputPort createUserInputPort;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewUserRequest user) {
        log.info("{} - create - message: init workflow to save a new user in kero infrastructure, user.email: {}, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), user.email(), DateUtils.getCurrent());
        this.createUserInputPort.create(NewUserRequestMapper.toDomain(user));

        log.info("{} - create - message: end workflow to save a new user in kero infrastructure, user.email: {}, at: {}, status: 201 CREATED ----> HTTP/END (POST)", getClass().getSimpleName(), user.email(), DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
