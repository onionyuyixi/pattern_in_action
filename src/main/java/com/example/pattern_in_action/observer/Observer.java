package com.example.pattern_in_action.observer;

/**
 * 1 更新内容
 * 2 选择主题
 */
public interface Observer<T> {

    void receiveMsg();

    void setSubject(Subject<T> subject);

}
