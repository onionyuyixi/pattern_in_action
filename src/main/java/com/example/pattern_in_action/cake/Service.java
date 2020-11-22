package com.example.pattern_in_action.cake;

public interface Service <T extends Service<?>>{

    T getThis();
}
