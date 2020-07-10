package com.example.pattern_in_action.mediator;

public interface Mediator {

    void addUser(User user); //传召人员

    void removeUser(User user); //剔除人员

    void speak(String msg, User user); // 一人发言

}
