package com.example.pattern_in_action.cake.impl;

import com.example.pattern_in_action.cake.EmptyService;

public interface EmptyServiceImpl<T extends EmptyServiceImpl<?>> extends EmptyService<T> {

    @Override
    default String empty() {
        return "";
    }
}
