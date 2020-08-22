package com.example.pattern_in_action.abstract_document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertyType {

    FISH("fish"),
    SALT("salt"),
    OTHER_WATERS("otherWaters");

    String desc;
}
