package com.example.pattern_in_action.abstract_document;

import java.util.stream.Stream;

import static com.example.pattern_in_action.abstract_document.PropertyType.OTHER_WATERS;

public interface HasOtherWaters extends Water {

    default Stream<Object> getOtherWaters() {
        return getOtherWaters(OTHER_WATERS.getDesc(), JiaLingRiver::new);
    }
}
