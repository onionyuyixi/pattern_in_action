package com.example.pattern_in_action.state.impl;

import com.example.pattern_in_action.state.TVState;


public class TVOnState implements TVState {
    @Override
    public void action() {
        System.err.println("打开电视");
    }
}
