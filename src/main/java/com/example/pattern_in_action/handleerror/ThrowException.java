package com.example.pattern_in_action.handleerror;

public interface ThrowException<T, X extends Throwable> {

    void action(T t) throws X;
}
