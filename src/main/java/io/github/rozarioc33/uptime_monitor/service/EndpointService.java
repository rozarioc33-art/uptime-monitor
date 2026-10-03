package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;

public interface EndpointService {

    public EndpointResponse createEndpoint(EndpointRequest request);
}
