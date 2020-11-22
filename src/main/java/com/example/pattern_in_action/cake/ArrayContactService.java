package com.example.pattern_in_action.cake;

public interface ArrayContactService<T extends EmptyService<?>
        & ContactService<?>> extends Service<T> {

    String contact(String... array);
}
