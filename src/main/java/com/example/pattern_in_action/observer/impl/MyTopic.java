package com.example.pattern_in_action.observer.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.pattern_in_action.observer.Observer;
import com.example.pattern_in_action.observer.Subject;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTopic<T> implements Subject<T> {

    private List<Observer> observers;
    private T message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic() {
        this.observers = Lists.newArrayList();
    }

    @Override
    public void register(Observer observer) {
        if (observer == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if (!observers.contains(observer)) observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        synchronized (MUTEX) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        if (!CollectionUtils.isEmpty(observersLocal)) {
            for (Observer obj : observersLocal) {
                obj.receiveMsg();
            }
        }
    }

    @Override
    public T getMsg(Observer obj) {
        return this.message;
    }

    @Override
    public void postMessage(T msg) {
        System.out.println("Message Posted to Topic:" + JSONObject.toJSONString(msg));
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }
}
