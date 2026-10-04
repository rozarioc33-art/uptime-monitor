package io.github.rozarioc33.uptime_monitor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EndpointRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    @Positive
    private Integer intervalSeconds = 30;

    @Positive
    private Integer timeoutSeconds = 5;
}
