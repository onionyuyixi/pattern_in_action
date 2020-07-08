package com.example.pattern_in_action.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompositeCar extends CarDecorator {


    public CompositeCar(Car car, String name) {
        super(car, name);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.err.println("我是组合车");
    }
}
