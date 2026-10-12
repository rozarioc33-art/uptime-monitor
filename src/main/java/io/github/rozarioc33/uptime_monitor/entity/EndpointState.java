package io.github.rozarioc33.uptime_monitor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "endpoint_states")

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EndpointState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "endpoint_id", nullable = false, unique = true)
    private Endpoint endpoint;

    @Column(nullable = false)
    private int consecutiveFailures = 0;

    @Column(nullable = false)
    private boolean healthy = true;

    private LocalDateTime lastCheckedAt;
}
