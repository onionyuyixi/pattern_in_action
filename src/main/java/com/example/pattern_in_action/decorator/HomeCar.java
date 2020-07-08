package com.example.pattern_in_action.decorator;

import lombok.Data;

@Data
public class HomeCar extends CarDecorator {

    private static final long serialVersionUID = -5449756483972459850L;

    public HomeCar(Car car, String name) {
        super(car, name);
    }

    public HomeCar() {
    }

    @Override
    public void assemble() {
        super.assemble();
        System.err.println("i am home car ");
    }
}
