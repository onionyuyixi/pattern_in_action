package com.example.pattern_in_action.observer.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.pattern_in_action.observer.Observer;
import com.example.pattern_in_action.observer.Subject;

public class MyTopicObserver<T> implements Observer<T> {

    private String name;
    //通过这里的变量引用 分散了传参的动作 好！
    private Subject<T> topic;

    public MyTopicObserver(String name) {
        this.name = name;
    }

    @Override
    public void receiveMsg() {
        // 通过topic来直接获取msg 是合理的节约的紧凑 颇像日本人在狭促里寻得圆满
        T msg = topic.getMsg(this);
        if (msg == null) {
            System.out.println(name + ":: No new message");
        } else
            System.out.println(name + ":: Consuming message::" + JSONObject.toJSONString(msg));
    }

    @Override
    public void setSubject(Subject subject) {
        topic = subject;
    }
}
