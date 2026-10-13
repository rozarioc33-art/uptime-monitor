package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointHealthResponse;
import io.github.rozarioc33.uptime_monitor.entity.EndpointState;
import io.github.rozarioc33.uptime_monitor.exception.CheckResultNotFoundException;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.mapper.EndpointStateMapper;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointStateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EndpointStateServiceImpl implements EndpointStateService{


    private final EndpointStateRepository endpointStateRepository;
    private final EndpointRepository endpointRepository;
    private final EndpointStateMapper endpointStateMapper;

    public EndpointStateServiceImpl(
            EndpointStateRepository endpointStateRepository,
            EndpointRepository endpointRepository,
            EndpointStateMapper endpointStateMapper
    ) {

        this.endpointStateRepository = endpointStateRepository;
        this.endpointRepository = endpointRepository;
        this.endpointStateMapper = endpointStateMapper;
    }

    @Override
    public EndpointHealthResponse getEndpointHealth(Long endpointId) {

        endpointRepository.findById(endpointId)
                .orElseThrow(() -> new EndpointNotFoundException(endpointId));

        EndpointState endpointState = endpointStateRepository.findByEndpointId(endpointId)
                .orElseThrow(() ->
                    new CheckResultNotFoundException(endpointId)
            );

            return endpointStateMapper.toEndpointHealthResponse(endpointState);
    }
}
