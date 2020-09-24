package com.example.pattern_in_action.execute_around;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public interface CoreAction<T, U, R> extends BiFunction<T, U, R> {

    ConcurrentHashMap map = new ConcurrentHashMap<>();

    CoreAction<String, Object, Object> saveAction = (s, obj) -> map.putIfAbsent(s, obj);

    CoreAction<String, Object, Object> getAction = (s, obj) -> map.get(s);


}
