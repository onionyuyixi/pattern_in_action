package com.example.pattern_in_action.acyclic_visitor.impl;

import com.example.pattern_in_action.acyclic_visitor.AllVisitor;
import com.example.pattern_in_action.acyclic_visitor.NumberData;
import com.example.pattern_in_action.acyclic_visitor.StringData;

public class AllVisitorImpl implements AllVisitor {

    @Override
    public void visitNumber(NumberData numberData) {
        System.err.println("AllVisitor---->NumberData---->"+numberData.getData());
    }

    @Override
    public void visitString(StringData stringData) {
        System.err.println("AllVisitor---->StringData---->"+stringData.getData());
    }
}
