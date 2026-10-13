package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;

public interface LatestCheckService {

    LatestCheckResponse getLatestCheck(Long endpointId);
}
