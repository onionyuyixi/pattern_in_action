package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.state.TVState;
import com.example.pattern_in_action.state.impl.TVContext;
import com.example.pattern_in_action.state.impl.TVOffState;
import com.example.pattern_in_action.state.impl.TVOnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state/")
public class StateController {

    @Autowired
    @Qualifier("tvContext")
    TVState tvContext;

    @GetMapping
    public Object testState() {
        TVOnState tvOnState = new TVOnState();
        TVOffState tvOffState = new TVOffState();
        TVContext context = (TVContext) tvContext;
        context.setTvState(tvOffState);
        context.action();
        context.setTvState(tvOnState);
        context.action();
        return "okay";
    }
}
