package com.example.pattern_in_action.execute_around;

import java.util.concurrent.ConcurrentHashMap;

public class GetAction extends AbstractExecuteTemplate {


    public GetAction(ConcurrentHashMap map) {
        super(map);
    }

    @Override
    <T, U, R> R doCoreAction(T t, U u) {
        return (R) map.get(t);
    }
}
