package io.github.rozarioc33.uptime_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EndpointHealthResponse {

    private Long id;
    private boolean healthy;
    private int consecutiveFailures;
    private LocalDateTime lastCheckedAt;
}
