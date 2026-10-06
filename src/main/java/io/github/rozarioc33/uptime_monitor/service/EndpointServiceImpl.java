package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.mapper.EndpointMapper;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EndpointServiceImpl implements EndpointService{

    private final EndpointRepository endpointRepository;
    private final EndpointMapper endpointMapper;

    public EndpointServiceImpl
            (EndpointRepository endpointRepository,
             EndpointMapper endpointMapper) {

        this.endpointRepository = endpointRepository;
        this.endpointMapper = endpointMapper;

    }


    @Override
    public EndpointResponse createEndpoint(EndpointRequest request) {

        Endpoint endpoint = endpointMapper.toEntity(request);

        Endpoint savedEndpoint = endpointRepository.save(endpoint);

        return endpointMapper.toResponse(savedEndpoint);
    }

    @Override
    public EndpointResponse getEndpointById(Long id) {

        Endpoint endpoint = endpointRepository.findById(id)
                .orElseThrow(() -> new EndpointNotFoundException(id));

        return endpointMapper.toResponse(endpoint);
    }

    @Override
    public List<EndpointResponse> getAllEndpoints() {

        List<Endpoint> endpoints = endpointRepository.findAll();
        List<EndpointResponse> responses = new ArrayList<>();

        for (Endpoint endpoint : endpoints) {
            responses.add(endpointMapper.toResponse(endpoint));
        }

        return responses;
    }

    @Override
    public EndpointResponse updateEndpoint(Long id, EndpointRequest request) {

        Endpoint endpoint = endpointRepository.findById(id)
                .orElseThrow(() -> new EndpointNotFoundException(id));

        endpoint.setName(request.getName());
        endpoint.setUrl(request.getUrl());
        endpoint.setIntervalSeconds(request.getIntervalSeconds());
        endpoint.setTimeoutSeconds(request.getTimeoutSeconds());

        Endpoint savedEndpoint = endpointRepository.save(endpoint);

        return endpointMapper.toResponse(savedEndpoint);

    }
}
