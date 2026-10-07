package io.github.rozarioc33.uptime_monitor.monitoring;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EndpointChecker {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public CheckResult check(Endpoint endpoint) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint.getUrl()))
                .timeout(Duration.ofSeconds(endpoint.getTimeoutSeconds()))
                .GET()
                .build();

        try {

            long startTime = System.nanoTime();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            long endTime = System.nanoTime();
            long responseTimeMs = (endTime - startTime) / 1_000_000;

            int statusCode = response.statusCode();

            boolean success = statusCode >= 200 && statusCode < 300;

            CheckResult checkResult = new CheckResult();

            checkResult.setEndpoint(endpoint);
            checkResult.setStatusCode(statusCode);
            checkResult.setResponseTimeMs(responseTimeMs);
            checkResult.setCheckedAt(LocalDateTime.now());

            return checkResult;

        } catch (IOException | InterruptedException e) {


        }

        return null;
    }
}
