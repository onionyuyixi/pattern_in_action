package com.example.pattern_in_action.cake;

public interface EmptyService<T extends EmptyService<?>> extends Service<T> {

    String empty();


}
