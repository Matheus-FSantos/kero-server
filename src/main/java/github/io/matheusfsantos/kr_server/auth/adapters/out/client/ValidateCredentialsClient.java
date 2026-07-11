package github.io.matheusfsantos.kr_server.auth.adapters.out.client;

import github.io.matheusfsantos.kr_server.auth.adapters.in.http.impl.request.CredentialsRequest;
import github.io.matheusfsantos.kr_server.auth.adapters.out.client.response.UserIdentityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "validate-credentials-client", url = "${modules.base-url}")
public interface ValidateCredentialsClient {
    @GetMapping("/users/validate-credentials")
    ResponseEntity<UserIdentityResponse> validate(@RequestBody CredentialsRequest credentialsRequest);
}
