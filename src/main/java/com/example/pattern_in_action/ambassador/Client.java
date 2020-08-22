package com.example.pattern_in_action.ambassador;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Client {

    private final Ambassador ambassador = new Ambassador();

    public void callLogicService(Integer num) {
        ambassador.doLogic(num);
    }

}
