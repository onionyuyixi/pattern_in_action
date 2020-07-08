package com.example.pattern_in_action.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThirdAccumulator extends AccumulatorDecorator {

    double thirdWeight;

    public ThirdAccumulator(Accumulator accumulator, int number, double thirdWeight) {
        super(accumulator, number);
        this.thirdWeight = thirdWeight;
    }

    @Override
    public double compute() {
        double add = super.compute();
        sum = add * thirdWeight;
        return sum;
    }
}
