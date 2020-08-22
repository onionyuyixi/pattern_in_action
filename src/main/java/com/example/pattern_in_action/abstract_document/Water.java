package com.example.pattern_in_action.abstract_document;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Water {


    void addProperty(String key,Object object);

    Object getProperty(String key);

    <T> Stream<T> getOtherWaters(String key, Function<Map<String, Object>, T> function);

}
