package github.io.matheusfsantos.kr_server.auth.adapters;

import feign.FeignException;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.mapper.CredentialsRequestMapper;
import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.auth.adapters.out.client.ValidateCredentialsClient;
import github.io.matheusfsantos.kr_server.auth.adapters.out.client.mapper.UserIdentityResponseMapper;
import github.io.matheusfsantos.kr_server.auth.adapters.out.client.response.UserIdentityResponse;
import github.io.matheusfsantos.kr_server.auth.application.core.model.Credentials;
import github.io.matheusfsantos.kr_server.auth.application.core.model.UserIdentity;
import github.io.matheusfsantos.kr_server.auth.application.core.model.exception.InternalServiceException;
import github.io.matheusfsantos.kr_server.auth.application.ports.out.ValidateCredentialsOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCredentialsAdapter implements ValidateCredentialsOutputPort {
    private final ValidateCredentialsClient client;

    @Override
    public UserIdentity validate(Credentials credentials) throws InternalServiceException {
        try {
            CredentialsRequest credentialsRequest = CredentialsRequestMapper.toRequest(credentials);
            ResponseEntity<UserIdentityResponse> response = this.client.validate(credentialsRequest);
            if(response.getBody() == null || !response.getStatusCode().equals(HttpStatus.ACCEPTED)) throw new InternalServiceException("An unexpected error occurred while querying the provided credentials. Please wait a few moments and/or try again with different sign-in credentials.");

            return UserIdentityResponseMapper.toDomain(response.getBody());
        } catch (FeignException e) {
            log.error("{} - validate - message: an error occurred while validating the provided credentials, credentials.email: {}", getClass().getSimpleName(), credentials.email());
            log.debug("[{} - validate - ex.message: {}]", getClass().getSimpleName(), e.getMessage(), e);

            if(e instanceof FeignException.NotFound) throw new InternalServiceException(String.format("User with email %s not found", credentials.email()));
            throw new InternalServiceException("An unexpected error occurred while querying the provided credentials. Please wait a few moments and/or try again with different sign-in credentials.");
        }
    }
}
