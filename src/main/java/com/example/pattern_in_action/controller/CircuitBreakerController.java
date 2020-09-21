//package com.example.pattern_in_action.controller;
//
//import com.example.pattern_in_action.circuit_breaker.CircuitBreaker;
//import com.example.pattern_in_action.circuit_breaker.MonitoringService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/circuitBreaker/")
//public class CircuitBreakerController {
//
//
//    @GetMapping("callService")
//    public void callService() {
//        var obj = new MonitoringService();
//        var circuitBreaker = new CircuitBreaker(3000, 1, 2000 * 1000 * 1000);
//        var serverStartTime = System.nanoTime();
//        while (true) {
//            System.err.println(obj.localResourceResponse());
//            System.err.println(obj.remoteResourceResponse(circuitBreaker, serverStartTime));
//            System.err.println(circuitBreaker.getState());
//            try {
//                Thread.sleep(5 * 1000);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//    }
//}
