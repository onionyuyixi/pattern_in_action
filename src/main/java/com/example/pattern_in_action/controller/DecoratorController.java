package com.example.pattern_in_action.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pattern_in_action.decorator.*;
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


    @GetMapping("accumulator")
    public Object testAccumulator(){
        BaseAccumulator base = new BaseAccumulator(0);
        FirstAccumulator firstAccumulator = new FirstAccumulator(base, 1, 0.1);
        SecondAccumulator secondAccumulator = new SecondAccumulator(firstAccumulator, 2, 0.2);
        ThirdAccumulator thirdAccumulator = new ThirdAccumulator(secondAccumulator, 3, 3.0);
        double add = thirdAccumulator.compute();
        return add;
    }



}
