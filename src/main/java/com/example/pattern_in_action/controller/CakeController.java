package com.example.pattern_in_action.controller;


import com.example.pattern_in_action.cake.impl.ArrayContactServiceImpl;
import com.example.pattern_in_action.cake.impl.ContactServiceImpl;
import com.example.pattern_in_action.cake.impl.EmptyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cake")
public class CakeController {


    @GetMapping
    public Object test1() {
        TestImpl test = new TestImpl();
        int length = test.empty().length();
        String concat = test.concat("test", "cake");
        String contact1 = test.contact("1", "0", "k");
        return concat + "----"+ contact1;
    }


}


class TestImpl implements EmptyServiceImpl<TestImpl>, ContactServiceImpl<TestImpl>, ArrayContactServiceImpl<TestImpl> {

    @Override
    public TestImpl getThis() {
        return this;
    }


}