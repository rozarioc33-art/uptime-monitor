package io.github.rozarioc33.uptime_monitor.controller;

import io.github.rozarioc33.uptime_monitor.service.MonitoringServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final MonitoringServiceImpl monitoringServiceImpl;

    public MonitoringController(MonitoringServiceImpl monitoringServiceImpl) {

        this.monitoringServiceImpl = monitoringServiceImpl;
    }

    @PostMapping("/endpoints/{id}/check")
    public void checkEndpoint(@PathVariable Long id) {

        monitoringServiceImpl.checkEndpoint(id);
    }
}
