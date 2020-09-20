package com.example.pattern_in_action.circuit_breaker;

public class DelayedService {

    private final int delay;

    public DelayedService(int delay) {
        this.delay = delay;
    }

    public DelayedService() {
        this.delay = 60;
    }

    public String response(long serviceStartTime) {
        var currentTime = System.nanoTime();

        if ((currentTime - serviceStartTime) * 1.0 / (1000 * 1000 * 1000) < delay) {
            return "Delayed service is down";
        } else {
            return "Delayed service is working";
        }

    }
}
