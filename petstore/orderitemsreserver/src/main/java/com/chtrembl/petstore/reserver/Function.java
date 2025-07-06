package com.chtrembl.petstore.reserver;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 * <p>
 * Commands here
 * https://learn.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=macos%2Cbash%2Cazure-cli%2Cbrowser
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("orderitemsreserver")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            @BlobOutput(
                name = "outputItem",
                dataType = "binary",
                path = "orderitemsreserver/{Query.sessionId}.json",
                connection = "AzureWebJobsStorage"
            )
            OutputBinding<String> outputItem,
            final ExecutionContext context) {
        var sessionId = request.getQueryParameters().get("sessionId");
        var requestBody = request.getBody();

        if (sessionId == null) {
            context.getLogger().severe("Missing 'sessionId' query parameter.");
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Please pass a sessionId on the query string").build();
        }

        if (requestBody.isEmpty()) {
            context.getLogger().severe("Missing request body.");
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Please provide a request body").build();
        }

        var content = requestBody.get();

        context.getLogger().info("Saving session data for sessionId: " + sessionId + " with content length: " + content.length());

        outputItem.setValue(content);

        context.getLogger().info("Saved session data for sessionId: " + sessionId + " with content length: " + content.length());

        return request.createResponseBuilder(HttpStatus.OK).build();
    }
}
