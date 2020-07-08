package com.example.pattern_in_action.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pattern_in_action.decorator.BaseCar;
import com.example.pattern_in_action.decorator.CompositeCar;
import com.example.pattern_in_action.decorator.HomeCar;
import com.example.pattern_in_action.decorator.SportCar;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/decorator/")
@Api("装饰模式")
public class DecoratorController {


    @GetMapping("home")
    public Object testHomeDecorator(){
        BaseCar baseCar = new BaseCar();
        HomeCar homeCar = new HomeCar(baseCar,"home");
        homeCar.assemble();
        return JSONObject.toJSON(homeCar);
    }


    @GetMapping("sport")
    public Object testSportDecorator(){
        BaseCar baseCar = new BaseCar();
        SportCar sportCar = new SportCar(baseCar,"sport");
        sportCar.assemble();
        return JSONObject.toJSON(sportCar);
    }

    @GetMapping("composite")
    public Object testCompositeDecorator(){
        BaseCar baseCar = new BaseCar("base");
        SportCar sportCar = new SportCar(baseCar,"sport");
        HomeCar homeCar = new HomeCar(sportCar,"home");
        CompositeCar compositeCar = new CompositeCar(homeCar,"composite");
        compositeCar.assemble();
        return JSONObject.toJSON(compositeCar);
    }




}
