package io.github.rozarioc33.uptime_monitor.monitoring;

import io.github.rozarioc33.uptime_monitor.service.MonitoringServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitoringScheduler {

    private final MonitoringServiceImpl monitoringServiceImpl;

    public MonitoringScheduler(MonitoringServiceImpl monitoringServiceImpl) {

        this.monitoringServiceImpl = monitoringServiceImpl;
    }

    @Scheduled(fixedRate = 30000)
    public void runMonitoring() {
        monitoringServiceImpl.checkEnabledEndpoints();
    }
}
