package com.example.pattern_in_action.decorator;

import java.io.Serializable;

public class CarDecorator implements Car, Serializable {

    Car car;


    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        car.assemble();
    }


}
