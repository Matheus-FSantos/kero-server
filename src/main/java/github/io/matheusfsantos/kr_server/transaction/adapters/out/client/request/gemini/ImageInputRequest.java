package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageInputRequest(
    @JsonProperty("type") String type,
    @JsonProperty("data") String data,
    @JsonProperty("mime_type") String mime
) { }
