package com.example.pattern_in_action.abstract_document;

import java.util.Optional;

public interface HasSalt extends Water {

    default Optional<String> getSalt() {
        return Optional.ofNullable(((String) getProperty(PropertyType.SALT.getDesc())));
    }
}
