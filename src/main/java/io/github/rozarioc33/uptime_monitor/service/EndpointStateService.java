package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointHealthResponse;

public interface EndpointStateService {

    EndpointHealthResponse getEndpointHealth(Long endpointId);
}
