package com.example.pattern_in_action.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCar implements Car {
    String name;
    @Override
    public void assemble() {
        System.err.println("base car");
    }
}
