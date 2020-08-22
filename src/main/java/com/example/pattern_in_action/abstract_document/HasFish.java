package com.example.pattern_in_action.abstract_document;

import java.util.Optional;

import static com.example.pattern_in_action.abstract_document.PropertyType.FISH;

public interface HasFish extends Water {

    default Optional<String> getFish() {
        return Optional.ofNullable(((String) getProperty(FISH.getDesc())));
    }
}
