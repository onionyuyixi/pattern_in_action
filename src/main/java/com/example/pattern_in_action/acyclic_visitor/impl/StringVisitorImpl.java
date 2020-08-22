package com.example.pattern_in_action.acyclic_visitor.impl;

import com.example.pattern_in_action.acyclic_visitor.AllVisitor;
import com.example.pattern_in_action.acyclic_visitor.StringData;

public class StringVisitorImpl implements AllVisitor {

    @Override
    public void visitString(StringData stringData) {
        System.err.println("StringData------->"+stringData.getData());
    }
}
