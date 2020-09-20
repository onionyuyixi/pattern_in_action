package com.example.pattern_in_action.circuit_breaker;

public class MonitoringService {


    public String localResourceResponse() {
        return "Local Service is working";
    }

    public String remoteResourceResponse(CircuitBreaker circuitBreaker, long serviceStartTime) {
        try {
            return circuitBreaker.call("delayedService", serviceStartTime);
        } catch (Exception e) {
            return e.getMessage();
        }

    }


}
