package com.example.pattern_in_action.acyclic_visitor.impl;

import com.example.pattern_in_action.acyclic_visitor.AllVisitor;
import com.example.pattern_in_action.acyclic_visitor.NumberData;
import com.example.pattern_in_action.acyclic_visitor.StringData;

public class NumberVisitorImpl implements AllVisitor {

    @Override
    public void visitNumber(NumberData numberData) {
        System.err.println("NumberData---->"+numberData.getData());
    }

}
