package com.example.pattern_in_action.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enoughToBuy/")
@Api(value = "有得卖接口")
public class EnoughToSaleController {


    @PostMapping("callBack")
    public Object enoughToSaleCallBack(@RequestBody EnoughToSaleCallBackVO vo){
        return vo;
    }

}
