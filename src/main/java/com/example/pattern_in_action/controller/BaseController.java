package com.example.pattern_in_action.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/")
public class BaseController {

    @GetMapping("test")
    public Object test() {
        return "test";
    }
}
