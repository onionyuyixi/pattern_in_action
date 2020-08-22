package com.example.pattern_in_action.acyclic_visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NumberData extends Data{
    Integer data;
    @Override
    public void accept(Visitor visitor) {
        if(visitor instanceof NumberVisitor){
            ((NumberVisitor) visitor).visitNumber(this);
        }
    }
}
