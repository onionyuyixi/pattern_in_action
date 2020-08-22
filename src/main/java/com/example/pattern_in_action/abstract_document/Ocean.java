package com.example.pattern_in_action.abstract_document;

import java.util.Map;

public class Ocean extends AbstractWater implements HasSalt, HasFish, HasOtherWaters {
    public Ocean(Map<String, Object> properties) {
        super(properties);
    }
}
