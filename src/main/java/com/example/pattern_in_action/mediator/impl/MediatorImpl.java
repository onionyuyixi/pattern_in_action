package com.example.pattern_in_action.mediator.impl;

import com.example.pattern_in_action.mediator.Mediator;
import com.example.pattern_in_action.mediator.User;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class MediatorImpl implements Mediator {

    List<User> users;
    private final Object MUTEX = new Object();

    public MediatorImpl() {
        this.users = Lists.newArrayList();
    }

    @Override
    public void addUser(User user) {
        Assert.notNull(user, "成员user不能为null");
        synchronized (MUTEX) {
            users.add(user);
        }
    }

    @Override
    public void removeUser(User user) {
        synchronized (MUTEX) {
            users.remove(user);
        }

    }

    @Override
    public void speak(String msg, User user) {
        for (User listener : users) {
            if (listener != user) {
                listener.listen(msg);
            }
        }
    }
}
