package com.example.pattern_in_action.state.impl;

import com.example.pattern_in_action.state.TVState;


public class TVOffState implements TVState {
    @Override
    public void action() {
        System.err.println("关电视");
    }
}
