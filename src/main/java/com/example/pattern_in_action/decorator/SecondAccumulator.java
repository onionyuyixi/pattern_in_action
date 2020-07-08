package com.example.pattern_in_action.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecondAccumulator extends AccumulatorDecorator {

    double secondWeight;

    public SecondAccumulator(Accumulator accumulator, int number, double secondWeight) {
        super(accumulator, number);
        this.secondWeight = secondWeight;
    }

    @Override
    public double compute() {
        double add = super.compute();
        sum = add*(1-secondWeight);
        return sum;
    }
}
