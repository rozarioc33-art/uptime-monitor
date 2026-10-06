package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;

import java.util.List;

public interface EndpointService {

    public EndpointResponse createEndpoint(EndpointRequest request);

    public EndpointResponse getEndpointById(Long id);

    public List<EndpointResponse> getAllEndpoints();

    public EndpointResponse updateEndpoint(Long id, EndpointRequest request);

    public void deleteEndpoint(Long id);
}
