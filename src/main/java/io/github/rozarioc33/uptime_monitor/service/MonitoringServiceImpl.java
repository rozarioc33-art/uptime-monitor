package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.monitoring.EndpointChecker;
import io.github.rozarioc33.uptime_monitor.repository.CheckResultRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService{

    private final EndpointRepository endpointRepository;
    private final EndpointChecker endpointChecker;
    private final CheckResultRepository checkResultRepository;

    public MonitoringServiceImpl(EndpointRepository endpointRepository,
                                EndpointChecker endpointChecker,
                                 CheckResultRepository checkResultRepository) {

        this.endpointRepository = endpointRepository;
        this.endpointChecker = endpointChecker;
        this.checkResultRepository = checkResultRepository;
    }

    @Override
    public void checkEndpoint(Long endpointId) {

        Endpoint endpoint = endpointRepository.findById(endpointId)
                .orElseThrow(() -> new EndpointNotFoundException(endpointId));

        CheckResult checkResult = endpointChecker.check(endpoint);

        checkResultRepository.save(checkResult);
    }

}
