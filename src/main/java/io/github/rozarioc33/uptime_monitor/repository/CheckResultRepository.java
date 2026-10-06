package io.github.rozarioc33.uptime_monitor.repository;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {
}
