package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.ambassador.Client;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ambassador/")
public class AmbassadorController {


    @PostMapping("callService")
    public Object callService(@RequestParam("num") Integer num) {
        while (num >= 0) {
            Client client = new Client();
            client.callLogicService(num);
            num--;
        }
        return "ok";
    }
}
