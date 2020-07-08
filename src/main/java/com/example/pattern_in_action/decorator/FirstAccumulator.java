package com.example.pattern_in_action.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FirstAccumulator extends AccumulatorDecorator {

    double firstWeight;


    public FirstAccumulator(Accumulator accumulator, int number, double firstWeight) {
        super(accumulator, number);
        this.firstWeight = firstWeight;
    }

    @Override
    public double compute() {
        double add = super.compute();
        sum = add * (1 + firstWeight);
        return sum;
    }
}
