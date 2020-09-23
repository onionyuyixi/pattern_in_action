package com.example.pattern_in_action.monad;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private int age;
    private Sex sex;
    private String email;


}
