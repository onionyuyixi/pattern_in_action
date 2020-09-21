//package com.example.pattern_in_action.controller;
//
//import com.example.pattern_in_action.ambassador.Client;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@RestController
//@RequestMapping("/ambassador/")
//public class AmbassadorController {
//
//
//    @PostMapping("callService")
//    public Object callService(@RequestParam("num") Integer num) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(2000);
//        while (num >= 0) {
//            final var numCopy = num;
//            threadPool.execute(() -> {
//                Client client = new Client();
//                client.callLogicService(numCopy);
//            });
//            num--;
//        }
//        while (true) {
//            threadPool.shutdown();
//            if (threadPool.isTerminated()) {
//                return "ok";
//
//            }
//        }
//    }
//}
