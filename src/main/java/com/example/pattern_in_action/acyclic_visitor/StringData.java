package com.example.pattern_in_action.acyclic_visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class StringData extends Data{

    String data;

    @Override
   public void accept(Visitor visitor) {
        if(visitor instanceof StringVisitor){
            ((StringVisitor) visitor).visitString(this);
        }
    }
}
