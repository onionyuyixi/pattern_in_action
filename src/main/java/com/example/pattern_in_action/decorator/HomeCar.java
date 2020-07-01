package com.example.pattern_in_action.decorator;


public class HomeCar extends CarDecorator {

    private static final long serialVersionUID = -5449756483972459850L;

    public HomeCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.err.println("i am home car ");
    }
}
