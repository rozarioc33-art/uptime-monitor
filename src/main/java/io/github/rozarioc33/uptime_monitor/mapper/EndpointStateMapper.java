package io.github.rozarioc33.uptime_monitor.mapper;


import io.github.rozarioc33.uptime_monitor.dto.EndpointHealthResponse;
import io.github.rozarioc33.uptime_monitor.entity.EndpointState;
import org.springframework.stereotype.Component;

@Component
public class EndpointStateMapper {

    public EndpointHealthResponse toEndpointHealthResponse(EndpointState endpointState) {

        return new EndpointHealthResponse(
                endpointState.getEndpoint().getId(),
                endpointState.isHealthy(),
                endpointState.getConsecutiveFailures(),
                endpointState.getLastCheckedAt()
        );
    }
}
