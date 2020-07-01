package com.example.pattern_in_action.controller;

import com.example.pattern_in_action.visitor.EnoughToBuyItem;
import com.example.pattern_in_action.visitor.LoveRecoveryItem;
import com.example.pattern_in_action.visitor.Visitor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item/")
@Api(value = "回收接口")
public class ItemController extends BaseController {

    @Autowired
    @Qualifier("visitorImpl")
    Visitor visitor;

    @Autowired
    @Qualifier(value = "visitorImpl1")
    Visitor visitor1;

    @PostMapping("loveRecovery")
    @ApiOperation(value = "爱回收回收接口")
    public Object getLoveRecoveryUrl(@RequestBody LoveRecoveryItem item) {
        Object accept = item.accept(visitor);
        return accept;

    }

    @PostMapping("enoughToBuy")
    @ApiOperation(value = "有得卖回收接口")
    public Object getLoveRecoveryUrl(@RequestBody EnoughToBuyItem item) {
        Object accept = item.accept(visitor);
        return accept;
    }

    @Override
    @GetMapping("testItem")
    public Object test() {
        return "item";
    }
}
