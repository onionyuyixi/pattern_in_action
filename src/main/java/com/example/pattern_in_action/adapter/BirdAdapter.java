package com.example.pattern_in_action.adapter;

import org.springframework.stereotype.Component;

@Component("birdAdapter")
public class BirdAdapter extends Animal implements Adapter {
    @Override
    String howToPlay() {
        return "i believe i can fly ";
    }

    @Override
    public String play() {
        return howToPlay();
    }
}
