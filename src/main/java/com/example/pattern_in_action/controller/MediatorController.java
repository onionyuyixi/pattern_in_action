package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.mediator.Mediator;
import com.example.pattern_in_action.mediator.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mediator/")
public class MediatorController {

    @Autowired
    Mediator mediator;

    @GetMapping("test")
    public Object testMediator() {
        UserImpl onion = new UserImpl(mediator, "onion");
        UserImpl yuyixi = new UserImpl(mediator, "yuyixi");
        UserImpl yangcong = new UserImpl(mediator, "yangcong");

        mediator.addUser(onion);
        mediator.addUser(yuyixi);
        mediator.addUser(yangcong);

        onion.speak("孔子之门 忠恕");
        yuyixi.speak("孟子之道 恻隐");
        mediator.removeUser(onion);
        yangcong.speak("老庄无为");
        return "okay";
    }
}
