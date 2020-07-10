package com.example.pattern_in_action.mediator.impl;

import com.example.pattern_in_action.mediator.Mediator;
import com.example.pattern_in_action.mediator.User;

public class UserImpl extends User {


    public UserImpl(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void speak(String msg) {
        System.err.println(name + "发言" + msg);
        mediator.speak(msg, this);
    }

    @Override
    public void listen(String msg) {
        System.err.println(name+"接收信息" + msg);
    }
}
