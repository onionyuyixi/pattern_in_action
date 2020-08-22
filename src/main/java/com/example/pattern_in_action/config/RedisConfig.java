package com.example.pattern_in_action.config;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {


    @Bean
    public RedissonClient getRedissionClient() {
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .addSentinelAddress("redis://localhost:26379", "redis://localhost:26380", "redis://localhost:26381")
                .setTimeout(60000)
                .setCheckSentinelsList(false);
        return Redisson.create(config);
    }

    @Bean
    public RLiveObjectService getRLiveObjectService() {
        return getRedissionClient().getLiveObjectService();
    }


    public Object getObject() {
        return new Object();
    }


}
