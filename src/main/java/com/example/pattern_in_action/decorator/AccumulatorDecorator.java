package com.example.pattern_in_action.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccumulatorDecorator implements Accumulator {

    Accumulator accumulator;

    int number;

    double sum;

    public AccumulatorDecorator(Accumulator accumulator, int number) {
        this.accumulator = accumulator;
        this.number = number;
    }

    @Override
    public double compute() {
        double add = accumulator.compute();
        sum = sum + add + number;
        return sum;
    }
}
