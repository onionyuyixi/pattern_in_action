package com.example.pattern_in_action.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseAccumulator implements Accumulator {

    int sum;

    public double compute() {
        sum = 0;
        return sum;
    }

}
