package com.example.pattern_in_action.acyclic_visitor;

public interface NumberVisitor extends Visitor{

    default void visitNumber(NumberData numberData) {

    }
}
