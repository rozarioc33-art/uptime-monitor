package io.github.rozarioc33.uptime_monitor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_results")

@Data
public class CheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "endpoint_id", nullable = false)
    private  Endpoint endpoint;

    private Integer statusCode;

    private Long responseTimeMs;

    private Boolean success;

    private LocalDateTime checkedAt;
}
