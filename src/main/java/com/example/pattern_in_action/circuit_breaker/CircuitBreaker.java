package com.example.pattern_in_action.circuit_breaker;

public class CircuitBreaker {

    private State state;
    private final long timeOut;
    private final long retryTimePeriod;
    long lastFailureTime;
    int failureCount;
    private final long futureTime = 1000 * 1000 * 1000 * 1000;
    private final int failureThreshold;

    public CircuitBreaker(long timeOut, long retryTimePeriod, int failureThreshold) {
        this.state = State.CLOSED;
        this.timeOut = timeOut;
        this.retryTimePeriod = retryTimePeriod;
        this.failureThreshold = failureThreshold;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.failureCount = 0;


    }

    private void reset() {
        this.failureCount = 0;
        this.lastFailureTime = System.nanoTime() + futureTime;
        this.state = State.CLOSED;
    }

    private void recordFailure() {
        failureCount = failureCount + 1;
        this.lastFailureTime = System.nanoTime();
    }

    protected void setState() {
        if (failureCount > failureCount) {
            if (System.nanoTime() - lastFailureTime > retryTimePeriod) {
                state = State.HALF_OPEN;
            } else {
                state = State.OPEN;
            }
        } else {
            state = State.CLOSED;
        }
    }

    public State getState() {
        return state;
    }

    public void setStateForBypass(State state) {
        this.state = state;
    }

    public String call(String serviceToCall, long serviceStartTime) throws Exception {
        setState();
        if (state == State.OPEN) {
            return "当前服务" + serviceToCall + "已经无法正常提供响应";
        } else {
            if (serviceToCall.equals("delayedService")) {
                var delayedService = new DelayedService(100);
                var response = delayedService.response(serviceStartTime);
                if (response.split(" ")[3].equals("working")) {
                    reset();
                    return response;
                } else {
                    recordFailure();
                    throw new Exception("远端服务没有响应");
                }
            } else {
                throw new Exception("Unknown service name");
            }
        }
    }
}
