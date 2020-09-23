package com.example.pattern_in_action.monad;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


@Getter
public class GenericValidator<T> {

    private final T obj;

    private final List<String> exceptions = new ArrayList<>();

    private GenericValidator(T obj) {
        this.obj = obj;
    }

    public static <T> GenericValidator<T> unit(T t) {
        return new GenericValidator<>(Objects.requireNonNull(t));
    }


    public GenericValidator<T> validate(Predicate<T> p, String message) {
        if (!p.test(obj)) {
            exceptions.add(message);
        }
        return this;
    }


    public <U> GenericValidator<T> mapValidate(Function<T, U> f, Predicate<U> p, String message) {
        return validate(f.andThen(p::test)::apply, message);
    }


    public <U> GenericValidator<U> flatMap(Function<T, U> f, Function<U, GenericValidator<U>> validator) {
        return f.andThen(validator::apply).apply(obj);
    }

}
