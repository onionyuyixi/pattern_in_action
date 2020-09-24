package com.example.pattern_in_action.execute_around;

import java.util.concurrent.ConcurrentHashMap;

public class ExecuteAround {


    public static void main(String[] args) {
        Object save = executeAround(CoreAction.saveAction, "1", 1);

        System.err.println(save);

        Object get = executeAround(CoreAction.getAction, "1", null);

        System.err.println(get);


        //一下是模板模式的executeAround
        final ConcurrentHashMap map = new ConcurrentHashMap<>();
        AbstractExecuteTemplate saveAction = new SaveAction(map);
        Object save1 = saveAction.executeAround("1", 1);
        System.err.println(save1);
        AbstractExecuteTemplate getAction = new GetAction(map);
        Object get1 = getAction.executeAround("1", null);
        System.err.println(get1);

        //二者比較 高低自判  無須贅言

    }


    static <T, U, R> R executeAround(CoreAction<T, U, R> func, T t, U u) {
        System.err.println("開啓事務");
        R r = func.apply(t, u); //這裏可以自定義各種函數  參數太多的時候 就自己藉助@FunctionalInterface定義函數即可
        System.err.println("關閉事務");
        return r;
    }


}
