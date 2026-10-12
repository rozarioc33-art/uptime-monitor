package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointHealthResponse;
import io.github.rozarioc33.uptime_monitor.entity.EndpointState;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointStateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EndpointStateServiceImpl implements EndpointStateService{


    private final EndpointStateRepository endpointStateRepository;
    private final EndpointRepository endpointRepository;

    public EndpointStateServiceImpl(
            EndpointStateRepository endpointStateRepository,
            EndpointRepository endpointRepository
    ) {

        this.endpointStateRepository = endpointStateRepository;
        this.endpointRepository = endpointRepository;
    }

    @Override
    public EndpointHealthResponse getEndpointHealth(Long endpointId) {

        endpointRepository.findById(endpointId)
                .orElseThrow(() -> new EndpointNotFoundException(endpointId));

        EndpointState endpointState = endpointStateRepository.findByEndpointId(endpointId)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.SERVICE_UNAVAILABLE,
                        "Endpoint health state not found"
                )
            );

        return new EndpointHealthResponse(
                endpointState.getEndpoint().getId(),
                endpointState.isHealthy(),
                endpointState.getConsecutiveFailures(),
                endpointState.getLastCheckedAt()
        );
    }
}
