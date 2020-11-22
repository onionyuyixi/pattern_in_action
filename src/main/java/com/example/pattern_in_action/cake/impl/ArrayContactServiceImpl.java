package com.example.pattern_in_action.cake.impl;

import com.example.pattern_in_action.cake.ArrayContactService;
import com.example.pattern_in_action.cake.ContactService;
import com.example.pattern_in_action.cake.EmptyService;

import java.util.stream.Stream;

public interface ArrayContactServiceImpl<T extends EmptyService<?>
        & ContactService<?>> extends ArrayContactService<T> {

    @Override
    default String contact(String... array) {
        return Stream.of(array).reduce((a, b) -> getThis().concat(a, b)).orElse(getThis().empty());
    }
}
