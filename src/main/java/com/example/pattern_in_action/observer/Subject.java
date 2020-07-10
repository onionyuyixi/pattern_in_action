package com.example.pattern_in_action.observer;

/**
 * 1 成员管理
 * 2 触发通知
 * 3
 */
public interface Subject<T> {


    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers();

     T getMsg(Observer obj);

     void postMessage(T msg);


}
