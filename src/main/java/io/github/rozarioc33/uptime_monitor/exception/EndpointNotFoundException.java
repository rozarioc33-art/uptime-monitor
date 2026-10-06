package io.github.rozarioc33.uptime_monitor.exception;

public class EndpointNotFoundException extends RuntimeException {

    public EndpointNotFoundException(Long id) {
        super("Endpoint not found with id: " + id);
    }
}
