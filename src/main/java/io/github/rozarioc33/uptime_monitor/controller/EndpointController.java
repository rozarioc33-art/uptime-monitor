package io.github.rozarioc33.uptime_monitor.controller;

import io.github.rozarioc33.uptime_monitor.dto.EndpointRequest;
import io.github.rozarioc33.uptime_monitor.dto.EndpointResponse;
import io.github.rozarioc33.uptime_monitor.service.EndpointServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public EndpointResponse getEndpointById(@PathVariable Long id) {

        return endpointServiceImpl.getEndpointById(id);
    }

    @GetMapping
    public List<EndpointResponse> getAllEndpoints() {

        return endpointServiceImpl.getAllEndpoints();
    }

    @PutMapping("/{id}")
    public EndpointResponse updateEndpoint(
            @PathVariable Long id,
            @Valid @RequestBody EndpointRequest request
    ) {

        return endpointServiceImpl.updateEndpoint(id, request);
    }

}
