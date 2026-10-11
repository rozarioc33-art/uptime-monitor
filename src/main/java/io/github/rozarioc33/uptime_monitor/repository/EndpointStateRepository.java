package io.github.rozarioc33.uptime_monitor.repository;

import io.github.rozarioc33.uptime_monitor.entity.EndpointState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EndpointStateRepository extends JpaRepository<EndpointState, Long> {

    Optional<EndpointState> findByEndpointId(Long endpointId);
}
