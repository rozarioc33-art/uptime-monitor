package io.github.rozarioc33.uptime_monitor.controller;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;
import io.github.rozarioc33.uptime_monitor.service.LatestCheckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
public class LatestCheckController {

    private final LatestCheckService latestCheckService;

    public LatestCheckController(LatestCheckService latestCheckService) {

        this.latestCheckService = latestCheckService;
    }

    @GetMapping("/{endpointId}/checks/latest")
    public LatestCheckResponse getLatestCheck(@PathVariable Long endpointId) {

        return latestCheckService.getLatestCheck(endpointId);
    }

    @GetMapping("/{endpointId}/checks")
    public Page<LatestCheckResponse> getCheckHistory(
            @PathVariable Long endpointId,
            Pageable pageable
        ) {

        return latestCheckService.getCheckHistory(
                endpointId,
                pageable
        );
    }
}
