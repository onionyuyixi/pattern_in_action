package com.example.pattern_in_action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PatternInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatternInActionApplication.class, args);
    }

}
