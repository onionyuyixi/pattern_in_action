package com.example.pattern_in_action.interpreter.impl;

import com.example.pattern_in_action.interpreter.Interpreter;
import org.springframework.stereotype.Component;

@Component
public class InterpreterContext implements Interpreter {


    public String englishToChinese(String words) {
        System.err.println("原文---->" + words);
        return "翻译后-------" + words;
    }

    public String japaneseToChinese(String words) {
        System.err.println("原文---->" + words);
        return "翻译后-------" + words;
    }
}
