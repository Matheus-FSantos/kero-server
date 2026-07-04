package github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini.StepResponse;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini.UsageResponse;

import java.util.List;

public record GeminiResponse(
    @JsonProperty("id") String id,
    @JsonProperty("status") String status,
    @JsonProperty("usage") UsageResponse usage,
    @JsonProperty("created") String created,
    @JsonProperty("updated") String updated,
    @JsonProperty("service_tier") String serviceTier,
    @JsonProperty("steps") List<StepResponse> steps,
    @JsonProperty("object") String object,
    @JsonProperty("model") String model
) {}
