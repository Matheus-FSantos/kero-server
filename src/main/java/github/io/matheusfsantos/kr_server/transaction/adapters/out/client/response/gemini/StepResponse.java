package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StepResponse(
    @JsonProperty("signature") String signature,
    @JsonProperty("type") String type,
    @JsonProperty("content") List<ContentResponse> content
) {}
