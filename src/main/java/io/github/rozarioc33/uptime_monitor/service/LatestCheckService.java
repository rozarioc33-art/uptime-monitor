package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LatestCheckService {

    LatestCheckResponse getLatestCheck(Long endpointId);
    Page<LatestCheckResponse> getCheckHistory(
            Long endpointId,
            Pageable pageable
    );
}
