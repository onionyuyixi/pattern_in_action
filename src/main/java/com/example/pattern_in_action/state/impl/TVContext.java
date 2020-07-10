package com.example.pattern_in_action.state.impl;

import com.example.pattern_in_action.state.TVState;
import org.springframework.stereotype.Component;

@Component("tvContext")
public class TVContext implements TVState {

    TVState tvState;

    public void setTvState(TVState tvState) {
        this.tvState = tvState;
    }

    @Override
    public void action() {
        tvState.action();
    }
}
