package com.example.pattern_in_action.visitor;

public interface Item {
    Object accept(Visitor visitor);
}
