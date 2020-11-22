package com.example.pattern_in_action.cake.impl;

import com.example.pattern_in_action.cake.ContactService;

public interface ContactServiceImpl<T extends ContactServiceImpl<?>> extends ContactService<T> {

    @Override
    default String concat(String first, String second) {
        return first + " " + second;
    }
}
