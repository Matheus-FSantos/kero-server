package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContentResponse(
    @JsonProperty("text") String text,
    @JsonProperty("type") String type
) {}