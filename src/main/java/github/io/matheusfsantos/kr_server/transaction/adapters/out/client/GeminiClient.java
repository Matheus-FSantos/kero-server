package github.io.matheusfsantos.kr_server.transaction.adapters.out.client;

import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.GeminiRequest;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.GeminiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gemini-ai-client", url = "${spring.gemini.base-url}")
public interface GeminiClient {
    @PostMapping(path = "/interactions", consumes = "application/json", produces = "application/json")
    ResponseEntity<GeminiResponse> interact(
        @RequestHeader(name = "x-goog-api-key") String key,
        @RequestBody GeminiRequest body
    );
}
