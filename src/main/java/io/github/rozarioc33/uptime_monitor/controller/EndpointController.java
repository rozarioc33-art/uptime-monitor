package io.github.rozarioc33.uptime_monitor.controller;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;
import io.github.rozarioc33.uptime_monitor.service.EndpointServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointController {

    private final EndpointServiceImpl endpointServiceImpl;

    public EndpointController(EndpointServiceImpl endpointServiceImpl) {
        this.endpointServiceImpl = endpointServiceImpl;
    }

    @PostMapping
    public EndpointResponse createEndpoint(@Valid @RequestBody EndpointRequest request) {

        return endpointServiceImpl.createEndpoint(request);
    }
}
