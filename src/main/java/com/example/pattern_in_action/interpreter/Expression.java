package com.example.pattern_in_action.interpreter;

import com.example.pattern_in_action.interpreter.impl.InterpreterContext;

public interface Expression {

     String interpret(InterpreterContext ic);
}
