package com.example.pattern_in_action.interpreter.impl;


import com.example.pattern_in_action.interpreter.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractExpression implements Expression {

    String words;

}
