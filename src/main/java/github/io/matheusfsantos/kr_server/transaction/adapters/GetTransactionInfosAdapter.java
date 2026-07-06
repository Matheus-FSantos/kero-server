package github.io.matheusfsantos.kr_server.transaction.adapters;

import feign.FeignException;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.GeminiClient;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.GeminiRequest;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.gemini.ImageInputRequest;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.request.gemini.TextInputRequest;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.GeminiResponse;
import github.io.matheusfsantos.kr_server.transaction.adapters.out.client.response.gemini.ContentResponse;
import github.io.matheusfsantos.kr_server.transaction.application.core.constants.PromptConstants;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.Transaction;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.ExternalServiceException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.IllegalFileCastException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.MissingContentException;
import github.io.matheusfsantos.kr_server.transaction.application.core.model.exception.PayloadContractException;
import github.io.matheusfsantos.kr_server.transaction.application.core.utils.InputStreamUtils;
import github.io.matheusfsantos.kr_server.transaction.application.core.utils.ResponsesUtils;
import github.io.matheusfsantos.kr_server.transaction.application.ports.out.GetTransactionInfosOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetTransactionInfosAdapter implements GetTransactionInfosOutputPort {
    @Value("${spring.gemini.api-key}")
    private String KEY;
    private final GeminiClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Transaction get(InputStream file, String contentType) throws IllegalFileCastException, ExternalServiceException, PayloadContractException, MissingContentException {
        try {
            String base64File = InputStreamUtils.getBase64(file);
            GeminiRequest body = new GeminiRequest("gemini-3.1-flash-lite", new Object[]{
                    new TextInputRequest("text", PromptConstants.TRANSACTION_AI_CORE_PROMPT),
                    new ImageInputRequest("image", base64File, contentType)
            });

            ResponseEntity<GeminiResponse> response = this.client.interact(this.KEY, body);
            if(response.getBody() == null || response.getBody().steps() == null || response.getBody().steps().size() < 2) {
                log.error("{} - get - message: internal error - gemini response body or steps structure is missing.", getClass().getSimpleName());
                throw new PayloadContractException("Gemini response body or steps structure is missing or malformed");
            }

            Optional<ContentResponse> promptContent = response.getBody().steps().get(1).content().stream().findFirst();
            if (promptContent.isEmpty()) {
                log.error("{} - get - message: empty data - no text content found in step response.", getClass().getSimpleName());
                throw new MissingContentException("No content found in the second step of Gemini response");
            }

            String rawJson = promptContent.get().text();
            rawJson = ResponsesUtils.cleanResponse(rawJson);

            log.info("{} - get - message: mapping Gemini json text response to Transaction object...", getClass().getSimpleName());
            Transaction transaction = objectMapper.readValue(rawJson, Transaction.class);

            log.info("{} - get - message: transaction processed successfully!", getClass().getSimpleName());
            return transaction;
        }  catch (FeignException e) {
            log.error("{} - get - message: integration error communicating with Gemini api. Status: {}, e.message: {}", getClass().getSimpleName(), e.status(), e.getMessage());
            throw new ExternalServiceException(String.format("Gemini API integration failed with status %d", e.status()));
        }
    }
}
