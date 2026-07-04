package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ModalityTokenResponse(
    @JsonProperty("modality") String modality,
    @JsonProperty("tokens") int tokens
) {}
