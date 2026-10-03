package io.github.rozarioc33.uptime_monitor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "endpoints")

@Data
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String url;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer intervalSeconds = 30;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer timeoutSeconds = 5;

    @NotNull
    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
