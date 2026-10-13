package io.github.rozarioc33.uptime_monitor.mapper;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class EndpointMapper {

    public EndpointResponse toResponse(Endpoint endpoint) {

        EndpointResponse response = new EndpointResponse();

        response.setId(endpoint.getId());
        response.setName(endpoint.getName());
        response.setUrl(endpoint.getUrl());
        response.setIntervalSeconds(endpoint.getIntervalSeconds());
        response.setTimeoutSeconds(endpoint.getTimeoutSeconds());
        response.setEnabled(endpoint.getEnabled());
        response.setCreatedAt(endpoint.getCreatedAt());

        return response;
    }

    public Endpoint toEntity(EndpointRequest request) {

        Endpoint endpoint = new Endpoint();

        endpoint.setName(request.getName());
        endpoint.setUrl(request.getUrl());
        endpoint.setIntervalSeconds(request.getIntervalSeconds());
        endpoint.setTimeoutSeconds(request.getTimeoutSeconds());
        endpoint.setEnabled(
                request.getIntervalSeconds() != null
                        ? request.getEnabled()
                        : true
        );

        return endpoint;
    }
}
