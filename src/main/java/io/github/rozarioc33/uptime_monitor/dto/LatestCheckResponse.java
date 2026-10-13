package io.github.rozarioc33.uptime_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LatestCheckResponse {

    private Integer statusCode;
    private Long responseTimeMs;
    private boolean success;
    private LocalDateTime checkedAt;
}
