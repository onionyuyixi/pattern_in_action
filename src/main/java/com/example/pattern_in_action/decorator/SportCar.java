package com.example.pattern_in_action.decorator;

import lombok.Data;

@Data
public class SportCar extends CarDecorator {

    private static final long serialVersionUID = 8074283582894443891L;

    public SportCar(Car car, String name) {
        super(car, name);
    }

    public SportCar() {
    }

    @Override
    public void assemble() {
        super.assemble();
        System.err.println(" i am sport car ");
    }
}
