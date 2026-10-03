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
    private Integer intervalSeconds;

    @Positive
    private Integer timeoutSeconds;
}
