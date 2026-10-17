package io.github.rozarioc33.uptime_monitor.service;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;
import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import io.github.rozarioc33.uptime_monitor.exception.CheckResultNotFoundException;
import io.github.rozarioc33.uptime_monitor.exception.EndpointNotFoundException;
import io.github.rozarioc33.uptime_monitor.mapper.CheckResultMapper;
import io.github.rozarioc33.uptime_monitor.repository.CheckResultRepository;
import io.github.rozarioc33.uptime_monitor.repository.EndpointRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LatestCheckServiceImpl implements LatestCheckService {

    private final CheckResultRepository checkResultRepository;
    private final CheckResultMapper checkResultMapper;
    private final EndpointRepository endpointRepository;

    public LatestCheckServiceImpl(CheckResultRepository checkResultRepository,
                                  CheckResultMapper checkResultMapper,
                                  EndpointRepository endpointRepository) {

        this.checkResultRepository = checkResultRepository;
        this.checkResultMapper = checkResultMapper;
        this.endpointRepository = endpointRepository;
    }

    @Override
    public LatestCheckResponse getLatestCheck(Long endpointId) {

        endpointRepository.findById(endpointId)
                .orElseThrow(() ->
                        new EndpointNotFoundException(endpointId));

        CheckResult checkResult = checkResultRepository
                .findFirstByEndpointIdOrderByCheckedAtDesc(endpointId)
                .orElseThrow(() ->
                            new CheckResultNotFoundException(endpointId)
                        );

        return checkResultMapper.toLatestCheckResponse(checkResult);
    }

    @Override
    public Page<LatestCheckResponse> getCheckHistory(
            Long endpointId,
            Pageable pageable) {

        List<LatestCheckResponse> responses = new ArrayList<>();

        endpointRepository.findById(endpointId)
                .orElseThrow(() ->
                        new EndpointNotFoundException(endpointId)
                        );

        Page<CheckResult> checkResults =
                checkResultRepository
                    .findByEndpointIdOrderByCheckedAtDesc(
                        endpointId, pageable
                    );

        return checkResults.map(
                checkResult ->
                        checkResultMapper.toLatestCheckResponse(checkResult)
        );
    }
}