package io.github.rozarioc33.uptime_monitor.mapper;

import io.github.rozarioc33.uptime_monitor.dto.LatestCheckResponse;
import io.github.rozarioc33.uptime_monitor.entity.CheckResult;
import org.springframework.stereotype.Component;

@Component
public class CheckResultMapper {

    public LatestCheckResponse toLatestCheckResponse(CheckResult checkResult) {

        return new LatestCheckResponse(
                checkResult.getStatusCode(),
                checkResult.getResponseTimeMs(),
                checkResult.getSuccess(),
                checkResult.getCheckedAt()
        );
    }
}
