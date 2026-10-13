package io.github.rozarioc33.uptime_monitor.exception;

public class CheckResultNotFoundException extends RuntimeException {
    public CheckResultNotFoundException(Long id) {

        super("No check result found for endpoint: " + id);
    }
}
