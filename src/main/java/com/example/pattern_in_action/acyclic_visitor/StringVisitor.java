package com.example.pattern_in_action.acyclic_visitor;

public interface StringVisitor extends Visitor{

   default void visitString(StringData stringData){

   }
}
