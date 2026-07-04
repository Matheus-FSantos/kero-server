package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UsageResponse(
    @JsonProperty("total_tokens") int totalTokens,
    @JsonProperty("total_input_tokens") int totalInputTokens,
    @JsonProperty("input_tokens_by_modality") List<ModalityTokenResponse> inputTokensByModality,
    @JsonProperty("total_cached_tokens") int totalCachedTokens,
    @JsonProperty("total_output_tokens") int totalOutputTokens,
    @JsonProperty("total_tool_use_tokens") int totalToolUseTokens,
    @JsonProperty("total_thought_tokens") int totalThoughtTokens
) {}
