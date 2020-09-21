package com.example.pattern_in_action.controller;


import com.example.pattern_in_action.combinator.User;
import com.example.pattern_in_action.combinator.UserValidation;
import com.example.pattern_in_action.combinator.UserValidationBetter;
import com.example.pattern_in_action.combinator.ValidationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.pattern_in_action.combinator.UserValidation.*;

@RestController
@RequestMapping("/combinator/")
public class CombinatorController {


    @GetMapping("test")
    public Object test() {
        User gregor = new User("Gregor", 30, "nicemail@gmail.com");
        Boolean flag = nameIsNotEmpty().and(emailContainsAtSign()).apply(gregor);
        return flag ? "yes" : "no";
    }


    @GetMapping("testBetter")
    public Object testBetter() {
        User gregor = new User("Gregor", 30, "nicemail@gmail.com");
        ValidationResult validationResult = UserValidationBetter.nameIsNotEmpty()
                .and(UserValidationBetter.emailContainsAtSign())
                .apply(gregor);
        return validationResult;
    }

    @GetMapping("testBetterAll")
    public Object testBetterAll() {
        User gregor = new User("Gregor", -1, "nicemail@gmail.com");
        ValidationResult validationResult = UserValidationBetter.all(UserValidationBetter.emailContainsAtSign(),
                        UserValidationBetter.nameIsNotEmpty(),
                        UserValidationBetter.ageMustGtZero())
                .apply(gregor);
        return validationResult;
    }

    @GetMapping("testAll")
    public Object testAll() {
        User gregor = new User("Gregor", -1, "nicemail@gmail.com");
        return UserValidation.all(emailContainsAtSign(), nameIsNotEmpty(), ageMustGtZero()).apply(gregor);
    }


}
