package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import io.github.rozarioc33.uptime_monitor.mapper.EndpointMapper;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import org.springframework.stereotype.Service;

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
}
