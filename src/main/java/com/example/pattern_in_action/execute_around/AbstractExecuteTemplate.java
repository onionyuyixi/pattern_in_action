package com.example.pattern_in_action.execute_around;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public abstract class AbstractExecuteTemplate {

    final ConcurrentHashMap map;

    <T, U, R> R executeAround(T t, U u) {
        System.err.println("模板--------------開啓事務");
        R r = doCoreAction(t, u);
        System.err.println("模板--------------關閉事務");
        return r;
    }

    abstract <T, U, R> R doCoreAction(T t, U u);

}
