package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeminiRequest(
    @JsonProperty("model") String model,
    @JsonProperty("input") Object[] inputs
) { }
