package com.example.pattern_in_action.controller;


import com.example.pattern_in_action.abstract_document.Ocean;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.example.pattern_in_action.abstract_document.PropertyType.*;

//@RestController("/abstractDocument")
//public class AbstractDocumentController {
//
//    @Autowired
//    RedissonClient redissonClient;
//
//
//    @GetMapping("/getOcean")
//    public Object getOcean() {
//        Map<String, Object> jiaLingProperties = Map.of(FISH.getDesc(), "jiaLingRiver has a special fish named Chun");
//        Map<String, Object> changJiangProperties = Map.of(FISH.getDesc(), "changJiangRiver has a special fish named Xun");
//
//        Map<String, Object> oceanProperties = Map.of(FISH.getDesc(), "ocean has fish",
//                SALT.getDesc(), "ocean has salt",
//                OTHER_WATERS.getDesc(),List.of(jiaLingProperties,changJiangProperties)
//        );
//        Ocean ocean = new Ocean(oceanProperties);
//        RBucket<Ocean> globalConfig = redissonClient.getBucket("globalConfig");
//        globalConfig.set(ocean);
//        return ocean;
//    }
//
//
//    @GetMapping("/serviceName")
//    public Object getConfigByServiceName(@RequestParam("serviceName") String serviceName){
//        RBucket<Ocean> globalConfig = redissonClient.getBucket("globalConfig");
//        Object property = globalConfig.get().getProperty(serviceName);
//        return property;
//    }

//}
