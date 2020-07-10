package com.example.pattern_in_action.interpreter.impl;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JapaneseExpression extends AbstractExpression {

    public JapaneseExpression(String words) {
        super(words);
    }

    @Override
    public String interpret(InterpreterContext ic) {
        return ic.japaneseToChinese(words);
    }
}
