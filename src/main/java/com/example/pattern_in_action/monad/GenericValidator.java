package com.example.pattern_in_action.monad;

import java.util.function.Function;
import java.util.function.Predicate;

public interface GenericValidator<T> {


    GenericValidator<T> validate(Predicate<T> p, String message);

    <U> GenericValidator<T> mapValidate(Function<T, U> f, Predicate<U> p, String message);

    <U> GenericValidator<U> flatMap(Function<T, U> f,Function<U,GenericValidator<U>> validator);


}
