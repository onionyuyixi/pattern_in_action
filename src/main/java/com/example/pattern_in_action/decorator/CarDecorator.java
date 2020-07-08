package com.example.pattern_in_action.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


//这里就是一个代理
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDecorator implements Car, Serializable {

    Car car;

    String name;


    @Override
    public void assemble() {
        car.assemble();
    }


}
