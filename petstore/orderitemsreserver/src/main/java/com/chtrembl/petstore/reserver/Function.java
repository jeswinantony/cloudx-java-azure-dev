package com.chtrembl.petstore.reserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public class Function {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @FunctionName("orderitemsreserver")
    public void run(
            @ServiceBusQueueTrigger(
                name = "msg",
                queueName = "orders",
                connection = "ServiceBusOrdersQueue"
            )
            String messagePayload,
            @BlobOutput(
                name = "outputItem",
                dataType = "binary",
                path = "orderitemsreserver/{sessionId}.json",
                connection = "AzureWebJobsStorage"
            )
            OutputBinding<String> outputItem,
            final ExecutionContext context) throws JsonProcessingException {
        context.getLogger().info("Java ServiceBus queue trigger function processed a message: " + messagePayload);

        OrderMessage request = objectMapper.readValue(messagePayload, OrderMessage.class);
        var sessionId = request.getSessionId();
        String content = request.getOrderJson();

        if (sessionId == null) {
            context.getLogger().severe("Missing 'sessionId' query parameter.");
            throw new IllegalArgumentException("Please provide a 'sessionId' query parameter");
        }

        if (content.isEmpty()) {
            context.getLogger().severe("Missing request body.");
            throw new IllegalArgumentException("Please provide a request body with the order JSON");
        }

        context.getLogger().info("Saving session data for sessionId: " + sessionId + " with content length: " + content.length());

        var retry = Retry.of("saveSessionDataRetry",
                RetryConfig.custom()
                        .maxAttempts(3)
                        .waitDuration(Duration.ofSeconds(2))
                        .build());
        var decorated = Retry.decorateRunnable(retry, () -> outputItem.setValue(content));
        decorated.run();

        context.getLogger().info("Saved session data for sessionId: " + sessionId + " with content length: " + content.length());
    }
}
