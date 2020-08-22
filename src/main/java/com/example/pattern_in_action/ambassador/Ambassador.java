package com.example.pattern_in_action.ambassador;

public class Ambassador implements LogicService {

    LogicService logicService;

    public Ambassador() {
        this.logicService = LogicServiceImpl.getInstance();
    }

    @Override
    public void doLogic(Integer num) {
        logicService.doLogic(num);
    }
}
