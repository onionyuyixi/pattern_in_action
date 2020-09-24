package com.example.pattern_in_action.execute_around;

import java.util.concurrent.ConcurrentHashMap;

public class SaveAction extends AbstractExecuteTemplate {


    public SaveAction(ConcurrentHashMap map) {
        super(map);
    }

    @Override
    <T, U, R> R doCoreAction(T t, U u) {
        return (R) map.putIfAbsent(t, u);
    }
}
