package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import io.github.rozarioc33.uptime_monitor.entity.Endpoint;
import io.github.rozarioc33.uptime_monitor.entity.EndpointState;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.monitoring.EndpointChecker;
import io.github.rozarioc33.uptime_monitor.repository.CheckResultRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointStateRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MonitoringServiceImpl implements MonitoringService{

    private final EndpointRepository endpointRepository;
    private final EndpointChecker endpointChecker;
    private final CheckResultRepository checkResultRepository;
    private final EndpointStateRepository endpointStateRepository;

    public MonitoringServiceImpl(EndpointRepository endpointRepository,
                                EndpointChecker endpointChecker,
                                 CheckResultRepository checkResultRepository,
                                 EndpointStateRepository endpointStateRepository) {

        this.endpointRepository = endpointRepository;
        this.endpointChecker = endpointChecker;
        this.checkResultRepository = checkResultRepository;
        this.endpointStateRepository = endpointStateRepository;
    }

    private EndpointState getOrCreateState(Endpoint endpoint) {

        Optional<EndpointState> existingState  = endpointStateRepository
                .findByEndpointId(endpoint.getId());

        return existingState.orElseGet(() -> {
            EndpointState state = new EndpointState();
            state.setEndpoint(endpoint);

            return endpointStateRepository.save(state);
        });

    }

    private void processCheckResult(Endpoint endpoint, CheckResult checkResult) {

        checkResultRepository.save(checkResult);

        EndpointState state = getOrCreateState(endpoint);

        if (checkResult.getSuccess()) {
            state.setConsecutiveFailures(0);
        } else {
            state.setConsecutiveFailures(
                    state.getConsecutiveFailures() + 1
            );
        }

        state.setHealthy(state.getConsecutiveFailures() < 3);
        state.setLastCheckedAt(checkResult.getCheckedAt());

        endpointStateRepository.save(state);
    }

    @Override
    public void checkEndpoint(Long endpointId) {

        Endpoint endpoint = endpointRepository.findById(endpointId)
                .orElseThrow(() -> new EndpointNotFoundException(endpointId));

        CheckResult checkResult = endpointChecker.check(endpoint);
        processCheckResult(endpoint, checkResult);
    }

    @Override
    public void checkEnabledEndpoints() {

        List<Endpoint> endpoints = endpointRepository.findByEnabledTrue();

        for (Endpoint endpoint: endpoints) {

            Optional<CheckResult> latestResult = checkResultRepository
                    .findFirstByEndpointIdOrderByCheckedAtDesc(endpoint.getId());

            if (latestResult.isEmpty()) {

                CheckResult checkResult = endpointChecker.check(endpoint);
                checkResultRepository.save(checkResult);
                continue;
            }

            CheckResult previousResult = latestResult.get();

            long elapsedTime = Duration.between(
                    previousResult.getCheckedAt(),
                    LocalDateTime.now()
            ).getSeconds();

            if (elapsedTime < endpoint.getIntervalSeconds()) {
                continue;
            }

            CheckResult checkResult = endpointChecker.check(endpoint);
            processCheckResult(endpoint, checkResult);
        }
    }

}
