package github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl;

import github.io.matheusfsantos.kr_server.auth.adapters.in.http.AuthController;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.mapper.CredentialsRequestMapper;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.TokenRequest;
import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.application.core.model.TokenInformation;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.AuthException;
import github.io.matheusfsantos.kr_server.auth.application.core.utils.DateUtils;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GenerateTokenInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.GetTokenInformationInputPort;
import github.io.matheusfsantos.kr_server.auth.application.ports.in.ValidateTokenInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/authentication")
public class AuthControllerImpl implements AuthController {
    private final GenerateTokenInputPort generateTokenInputPort;
    private final ValidateTokenInputPort validateTokenInputPort;
    private final GetTokenInformationInputPort getTokenInformationInputPort;

    @Override
    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@Valid @RequestBody CredentialsRequest credentialsRequest) throws AuthException {
        log.info("{} - processTransaction - message: init workflow to generate a new jwt token in kero infrastructure, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), DateUtils.getCurrent());
        Credentials credentials = CredentialsRequestMapper.toDomain(credentialsRequest);
        String token = this.generateTokenInputPort.fetch(credentials);

        log.info("{} - processTransaction - message: end workflow to generate a new jwt token in kero infrastructure, at: {}, status: 200 OK ----> HTTP/END (POST)", getClass().getSimpleName(), DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Override
    @PostMapping("/validate-token")
    public ResponseEntity<Void> validate(@RequestBody TokenRequest token) {
        log.info("{} - validate - message: init workflow to validate a generated jwt token in kero infrastructure, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), DateUtils.getCurrent());
        Boolean isValid = this.validateTokenInputPort.validate(token.token());
        HttpStatus status = isValid ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE;

        log.info("{} - validate - message: end workflow to validate a generated jwt token in kero infrastructure, at: {}, status: {} ----> HTTP/END (POST)", getClass().getSimpleName(), DateUtils.getCurrent(), isValid ? "202 ACCEPTED" : "406 NOT ACCEPTABLE");
        return ResponseEntity.status(status).build();
    }

    @Override
    @PostMapping("/token-information")
    public ResponseEntity<TokenInformation> tokenInformation(@RequestBody TokenRequest token) throws AuthException {
        log.info("{} - tokenInformation - message: init workflow to get a jwt token information in kero infrastructure, at: {} <---- HTTP/BEGIN (POST)", getClass().getSimpleName(), DateUtils.getCurrent());
        TokenInformation information = this.getTokenInformationInputPort.get(token.token());

        log.info("{} - tokenInformation - message: end workflow to get a jwt token information in kero infrastructure, at: {}, status: 200 OK ----> HTTP/END (POST)", getClass().getSimpleName(), DateUtils.getCurrent());
        return ResponseEntity.status(HttpStatus.OK).body(information);
    }
}
