package io.github.rozarioc33.uptime_monitor.controller;

import io.github.rozarioc33.uptime_monitor.dto.EndpointHealthResponse;
import io.github.rozarioc33.uptime_monitor.service.EndpointStateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointStateController {

    private final EndpointStateService endpointStateService;

    public EndpointStateController(EndpointStateService endpointStateService) {

        this.endpointStateService = endpointStateService;
    }

    @GetMapping("/{endpointId}/health")
    public EndpointHealthResponse getEndpointHealth(@PathVariable Long endpointId) {

        return endpointStateService.getEndpointHealth(endpointId);
    }
}
