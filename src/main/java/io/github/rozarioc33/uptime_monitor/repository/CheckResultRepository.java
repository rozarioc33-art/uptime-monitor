package io.github.rozarioc33.uptime_monitor.repository;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {

    Optional<CheckResult> findFirstByEndpointIdOrderByCheckedAtDesc(Long endpointId);

    Page<CheckResult> findByEndpointIdOrderByCheckedAtDesc(
            Long endpointId,
            Pageable pageable
    );
}
