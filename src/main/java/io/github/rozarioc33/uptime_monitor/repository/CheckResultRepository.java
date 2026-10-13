package io.github.rozarioc33.uptime_monitor.repository;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {

    Optional<CheckResult> findFirstByEndpointIdOrderByCheckedAtDesc(Long endpointId);

    List<CheckResult> findByEndpointIdOrderByCheckedAtDesc(Long endpointId);
}
