package com.example.pattern_in_action.controller;


import com.example.pattern_in_action.monad.GenericValidator;
import com.example.pattern_in_action.monad.GenericValidatorImpl;
import com.example.pattern_in_action.monad.Person;
import com.example.pattern_in_action.monad.Sex;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/monad/")
public class MonadController {


    @PostMapping(value = "testMonad")
    public Object testMonad(@RequestBody Person person) {
        GenericValidator<Person> validator = GenericValidatorImpl
                .unit(person)
                .mapValidate(Person::getSex, sex -> Objects.nonNull(sex) && Objects.nonNull(Sex.valueOf(sex.toString())), "性别不能为空")
                .mapValidate(Person::getEmail, email -> (!StringUtils.isEmpty(email) && email.contains("@")), "邮箱格式不正确")
                .mapValidate(Person::getName, name -> !StringUtils.isEmpty(name), "姓名不能为空");


        return validator;
    }
}
