package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.adapter.Adapter;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adapter/")
@Api(value = "适配器")
public class AdapterController {


    @Autowired
    @Qualifier("birdAdapter")
    Adapter birdAdapter;

    @GetMapping("bird")
    public String bird() {
        return birdAdapter.play();
    }
}
