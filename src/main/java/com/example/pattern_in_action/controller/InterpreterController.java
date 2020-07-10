package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.interpreter.Interpreter;
import com.example.pattern_in_action.interpreter.impl.EnglishExpression;
import com.example.pattern_in_action.interpreter.impl.JapaneseExpression;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interpreter/")
@Api(value = "interpreter")
public class InterpreterController {


    @Autowired
    Interpreter interpreter;

    @GetMapping("test")
    public Object testInterpreter() {
        EnglishExpression english = new EnglishExpression("english");
        JapaneseExpression japanese = new JapaneseExpression("japanese");
        String englishToChinese = interpreter.englishToChinese(english.getWords());
        String japaneseToChinese = interpreter.japaneseToChinese(japanese.getWords());
        return englishToChinese + "<------------>" + japaneseToChinese;


    }


}
