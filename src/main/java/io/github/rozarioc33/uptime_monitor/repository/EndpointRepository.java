package io.github.rozarioc33.uptime_monitor.repository;

import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
}
