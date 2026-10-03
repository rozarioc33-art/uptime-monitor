package io.github.rozarioc33.uptime_monitor.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EndpointResponse {

    private Long id;
    private String name;
    private String url;
    private Integer intervalSeconds;
    private Integer timeoutSeconds;
    private Boolean enabled;
    private LocalDateTime createdAt;
}
