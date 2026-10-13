package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;

import java.util.List;

public interface LatestCheckService {

    LatestCheckResponse getLatestCheck(Long endpointId);
    List<LatestCheckResponse> getCheckHistory(Long endpointId);
}
