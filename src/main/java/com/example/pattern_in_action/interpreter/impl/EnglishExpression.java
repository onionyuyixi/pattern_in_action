package com.example.pattern_in_action.interpreter.impl;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnglishExpression extends AbstractExpression {
    public EnglishExpression(String words) {
        super(words);
    }

    @Override
    public String interpret(InterpreterContext ic) {
        return ic.englishToChinese(words);
    }

}
