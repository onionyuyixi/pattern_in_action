package com.example.pattern_in_action.controller;


import com.example.pattern_in_action.acyclic_visitor.NumberData;
import com.example.pattern_in_action.acyclic_visitor.StringData;
import com.example.pattern_in_action.acyclic_visitor.impl.NumberVisitorImpl;
import com.example.pattern_in_action.acyclic_visitor.impl.StringVisitorImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acyclicVisitor")
public class AcyclicVisitorController {

    @GetMapping("/testString")
    public Object testString(@RequestParam("data") String data){
        StringData stringData = new StringData(data);
        StringVisitorImpl stringVisitor = new StringVisitorImpl();
        stringData.accept(stringVisitor);
        return data;
    }

    @GetMapping("/testNumber")
    public Object testNumber(@RequestParam("data") Integer data){
        NumberData numberData = new NumberData(data);
        NumberVisitorImpl numberVisitor = new NumberVisitorImpl();
        numberData.accept(numberVisitor);
        return data;
    }

    @GetMapping("/testAll")
    public Object testAll(@RequestParam("data") Integer data){
        NumberData numberData = new NumberData(data);
        StringData stringData = new StringData(String.valueOf(data));
        NumberVisitorImpl numberVisitor = new NumberVisitorImpl();
        StringVisitorImpl stringVisitor = new StringVisitorImpl();
        numberData.accept(numberVisitor);
        stringData.accept(stringVisitor);
        return data;
    }


}
