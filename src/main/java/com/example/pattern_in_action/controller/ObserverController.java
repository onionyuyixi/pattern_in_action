package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.observer.Subject;
import com.example.pattern_in_action.observer.impl.MyTopicObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/observer/")
public class ObserverController {

    @Autowired
    Subject<String> subject;


    @GetMapping("test")
    public Object testObserver() {
        MyTopicObserver<String> onion = new MyTopicObserver("onion");
        MyTopicObserver<Integer> yuyixi = new MyTopicObserver("yuyixi");
        MyTopicObserver<String> yangcong = new MyTopicObserver("yangcong");

        onion.setSubject(subject);
        yuyixi.setSubject(subject);
        yangcong.setSubject(subject);

        subject.register(onion);
        subject.register(yuyixi);
        subject.register(yangcong);

        subject.postMessage("韩非子，天赍口吃，覃思潜学，文章斐然");

        subject.unregister(yangcong);
        subject.postMessage("李斯，少贫恋位，始皇天崩，舠苇可杭");



        return "okay";


    }


}
