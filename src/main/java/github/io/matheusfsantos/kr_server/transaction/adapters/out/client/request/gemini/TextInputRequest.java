package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TextInputRequest(
    @JsonProperty("type") String type,
    @JsonProperty("text") String text
) { }
