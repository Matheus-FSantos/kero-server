package github.io.matheusfsantos.kr_server.user.adapters.in.http.impl;

import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.mapper.CredentialsRequestMapper;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.user.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.user.application.core.model.exception.UserException;
import github.io.matheusfsantos.kr_server.user.application.core.utils.DateUtils;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.UserController;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.mapper.NewUserRequestMapper;
import github.io.matheusfsantos.kr_server.user.adapters.in.http.impl.request.NewUserRequest;
import github.io.matheusfsantos.kr_server.user.application.ports.in.CreateUserInputPort;
import github.io.matheusfsantos.kr_server.user.application.ports.in.ValidateUserCredentialsInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserControllerImpl implements UserController {
    private final CreateUserInputPort createUserInputPort;
    private final ValidateUserCredentialsInputPort validateUserCredentialsInputPort;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody NewUserRequest user) throws UserException {
        log.info("{} - create - message: init workflow to save a new user in kero infrastructure, user.email: {}, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), user.email(), DateUtils.getCurrent());
        this.createUserInputPort.create(NewUserRequestMapper.toDomain(user));

        log.info("{} - create - message: end workflow to save a new user in kero infrastructure, user.email: {}, at: {}, status: 201 CREATED ----> HTTP/END (POST)", getClass().getSimpleName(), user.email(), DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PostMapping("validate-credentials")
    public ResponseEntity<UserIdentity> validateCredentials(@Valid @RequestBody CredentialsRequest credentials) throws UserException {
        log.info("{} - validateCredentials - message: init workflow to validate a user credentials in kero infrastructure, user.email: {}, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), credentials.email(), DateUtils.getCurrent());
        Optional<UserIdentity> identity = this.validateUserCredentialsInputPort.validate(CredentialsRequestMapper.toDomain(credentials));

        log.info("{} - validateCredentials - message: end workflow to validate a user credentials in kero infrastructure, user.email: {}, at: {}, status: 201 CREATED ----> HTTP/END (POST)", getClass().getSimpleName(), credentials.email(), DateUtils.getCurrent());
        return identity
            .map(i -> ResponseEntity.status(HttpStatus.ACCEPTED).body(i))
            .orElse(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
