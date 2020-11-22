package com.example.pattern_in_action.cake;

public interface ContactService<T extends ContactService<?>> extends Service<T> {

    String concat(String first, String second);
}
