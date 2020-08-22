package com.example.pattern_in_action.abstract_document;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractWater implements Water, Serializable {


    final Map<String, Object> properties;

    public AbstractWater(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public void addProperty(String key, Object object) {
        properties.put(key, object);
    }

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public <T> Stream<T> getOtherWaters(String key, Function<Map<String, Object>, T> function) {
        return Stream.ofNullable(properties.get(key))
                .filter(Objects::nonNull)
                .map(a -> (List<Map<String, Object>>) a)
                .findAny()
                .stream()
                .flatMap(Collection::stream)
                .map(function);
    }
}
