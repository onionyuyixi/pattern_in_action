package com.example.pattern_in_action.decorator;

public class BaseCar implements Car {
    @Override
    public void assemble() {
        System.err.println("base car");
    }
}
