package com.example.pattern_in_action.monad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericValidatorImpl<T> implements GenericValidator<T> {


    private final T obj;

    private final List<Throwable> exceptions = new ArrayList<>();

    private GenericValidatorImpl(T obj) {
        this.obj = obj;
    }

    public static <T> GenericValidator<T> unit(T t) {
        return new GenericValidatorImpl<>(Objects.requireNonNull(t));
    }

    @Override
    public GenericValidator<T> validate(Predicate<T> p, String message) {
        if (!p.test(obj)) {
            exceptions.add(new IllegalStateException(message));
        }
        return this;
    }


    @Override
    public <U> GenericValidator<T> mapValidate(Function<T, U> f, Predicate<U> p, String message) {
        return validate(f.andThen(p::test)::apply, message);
    }

    @Override
    public <U> GenericValidator<U> flatMap(Function<T, U> f, Function<U, GenericValidator<U>> validator) {
        return f.andThen(validator::apply).apply(obj);
    }

}
